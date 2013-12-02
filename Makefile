TOP_DIR = ../..
include $(TOP_DIR)/tools/Makefile.common
KB_RUNTIME ?= /kb/runtime
DEPLOY_RUNTIME ?= $(KB_RUNTIME)
KB_TOP ?= /kb/deployment
TARGET ?= $(KB_TOP)
CURR_DIR = $(shell pwd)
TARGET_DIR = $(TARGET)/services/$(SERVICE_NAME)
TARGET_PORT = 7108
THREADPOOL_SIZE = 20
SERVICE_NAME = $(shell basename $(CURR_DIR))
SERVICE_SPEC = ./kbase_cmonkey.spec
SERVICE_PORT = $(TARGET_PORT)
SERVICE_DIR = $(TARGET_DIR)
SERVLET_CLASS = us.kbase.cmonkey.CmonkeyServer
MAIN_CLASS = us.kbase.cmonkey.CmonkeyInvoker
SERVICE_PSGI = $(SERVICE_NAME).psgi
TPAGE_ARGS = --define kb_top=$(TARGET) --define kb_runtime=$(DEPLOY_RUNTIME) --define kb_service_name=$(SERVICE_NAME) --define kb_service_dir=$(SERVICE_DIR) --define kb_service_port=$(SERVICE_PORT) --define kb_psgi=$(SERVICE_PSGI)
SCRIPTS_TESTS = $(wildcard script-tests/*.t)
DEPLOY_CLUSTER = /kb/deployment/cmonkey
SCRIPTS_TESTS_CLUSTER = $(wildcard script-test-cluster/*.t)

default: compile

deploy: distrib deploy-client

deploy-cluster: compile-cluster deploy-cluster-logic test-cluster

compile-cluster: src lib
	./make_jar.sh $(MAIN_CLASS)

deploy-cluster-logic:
	rm -r $(DEPLOY_CLUSTER)
	mkdir $(DEPLOY_CLUSTER)
	mkdir $(DEPLOY_CLUSTER)/lib
	cp ./lib/*.jar $(DEPLOY_CLUSTER)/lib
	cp ./dist/cmonkey_cluster.jar $(DEPLOY_CLUSTER)

deploy-all: distrib deploy-client

deploy-client: deploy-libs deploy-scripts deploy-docs

deploy-libs: build-libs
	rsync --exclude '*.bak*' -arv lib/. $(TARGET)/lib/.

deploy-scripts:
	export KB_TOP=$(TARGET); \
	export KB_RUNTIME=$(DEPLOY_RUNTIME); \
	export KB_PERL_PATH=$(TARGET)/lib bash ; \
	for src in $(SRC_PERL) ; do \
		basefile=`basename $$src`; \
		base=`basename $$src .pl`; \
		echo install $$src $$base ; \
		cp $$src $(TARGET)/plbin ; \
		$(WRAP_PERL_SCRIPT) "$(TARGET)/plbin/$$basefile" $(TARGET)/bin/$$base ; \
	done

deploy-docs: build-docs
	-mkdir -p $(TARGET)/services/$(SERVICE_NAME)/webroot/.
	cp docs/*.html $(TARGET)/services/$(SERVICE_NAME)/webroot/.

build-docs: compile-docs
	pod2html --infile=lib/Bio/KBase/$(SERVICE_NAME)/Client.pm --outfile=docs/$(SERVICE_NAME).html

compile-docs: build-libs

build-libs:
	compile_typespec \
		--psgi $(SERVICE_PSGI)  \
		--impl Bio::KBase::$(SERVICE_NAME)::$(SERVICE_NAME)Impl \
		--service Bio::KBase::$(SERVICE_NAME)::Service \
		--client Bio::KBase::$(SERVICE_NAME)::Client \
		--py biokbase/$(SERVICE_NAME)/Client \
		--js javascript/$(SERVICE_NAME)/Client \
		$(SERVICE_SPEC) lib

test: test-scripts
	@echo "running script tests"

test-scripts:
	# run each test
	for t in $(SCRIPTS_TESTS) ; do \
		if [ -f $$t ] ; then \
			$(DEPLOY_RUNTIME)/bin/perl $$t ; \
			if [ $$? -ne 0 ] ; then \
				exit 1 ; \
			fi \
		fi \
	done


compile: src lib
	./make_war.sh $(SERVLET_CLASS)

distrib:
	@echo "Target folder: $(TARGET_DIR)"
	mkdir -p $(TARGET_DIR)
	mkdir -p $(TARGET_DIR)/data
	cp -f ./dist/service.war $(TARGET_DIR)
	cp -f ./glassfish_start_service.sh $(TARGET_DIR)
	cp -f ./glassfish_stop_service.sh $(TARGET_DIR)
	cp -f ./data/KEGG_taxonomy $(TARGET_DIR)/data/
	echo "./glassfish_start_service.sh $(TARGET_DIR)/service.war $(TARGET_PORT) $(THREADPOOL_SIZE)" > $(TARGET_DIR)/start_service.sh
	chmod +x $(TARGET_DIR)/start_service.sh
	echo "./glassfish_stop_service.sh $(TARGET_PORT)" > $(TARGET_DIR)/stop_service.sh
	chmod +x $(TARGET_DIR)/stop_service.sh

test-cluster:
	@echo "nothing to test"

clean:
	@echo "nothing to clean"
	
include $(TOP_DIR)/tools/Makefile.common.rules	
