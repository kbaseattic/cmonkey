#!/usr/bin/perl

#This is a command line testing script


use strict;
use warnings;

use Test::More tests => 3;
use Test::Cmd;
use JSON;
use Bio::KBase::AuthToken;
use Bio::KBase::AuthUser;
use Bio::KBase::workspace::Client;


my $url = "\"http://localhost:7112/\"";
my $bin  = "scripts";

my $ws = "\"AKtest\"";
my $user = "aktest";
my $pw = "1475rokegi";
my $series_ref = "\"AKtest/test_Halobacterium_sp_expression_series\"";
my $genome_ref = "\"AKtest/Halobacterium_sp_NRC-1\"";

my $auth_user = Bio::KBase::AuthUser->new();
my $token = Bio::KBase::AuthToken->new( user_id => $user, password => $pw);
$auth_user->get( token => $token->token );

if ($token->error_message){
	print $token->error_message."\n\n";
	exit(1);
};

#1
my $tes = Test::Cmd->new(prog => "$bin/run_cmonkey.pl", workdir => '', interpreter => '/kb/runtime/bin/perl');
ok($tes, "creating Test::Cmd object for run_cmonkey");
$tes->run(args => "--url=$url --ws=$ws --input=$series_ref --genome=$genome_ref");
ok($? == 0,"Running cmonkey");
my $tem=$tes->stdout;
print "Job ID:\t",$tem,"\n";
ok($tem =~ /[1-9]+/, "Cmonkey runs OK");

