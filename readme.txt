Module Cmonkey version 1.0 (created Aug 2013)
This module provides a method for work with cMonkey biclustering tool from the Baliga Lab at the Institute for Systems Biology. cMonkey takes as input gene expression data, promoter sequences, and gene-gene functional association/physical interaction data to find biclusters of conditionally co-regulated genes.

To install Cmonkey service, run commands:
make
make deploy

Deployment of Cmonkey service requires typecomp (dev-prototype branch) and java type generator (dev branch).
Cmonkey server calls AWE client to run cMonkey tool. To install Cmonkey service back-end for AWE client, clone cmonkey.git repo on the host running AWE client and run command 'make deploy-jar'. This command will work with master branches of typecomp and java type generator as well.
URL of AWE client can be changed in Makefile. Run "make", "make deploy" and then restart the service to apply changes.

Requirements for cMonkey installation on AWE server:
cmonkey-python (installation script in bootstrap.git repo: ./kb_cmonkey/build.cmonkey)

cMonkey dependencies:
scipy 0.9.0 or higher (apt-get install python-scipy)
numpy 1.6.0 or higher (apt-get install python-numpy)
MySQLdb 1.2.3 or higher
BeautifulSoup 3.2.0 or higher (apt-get install python-beautifulsoup)
R 2.14.1 or higher (apt-get install r-base)
rpy2 2.2.1 or higher (apt-get install python-rpy2)
MEME 4.3.0 or 4.8.1 or higher (installation script in bootstrap.git repo: ./kb_meme/build.meme)
csh for running MEME (apt-get install csh)

