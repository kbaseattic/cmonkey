#!/usr/bin/perl

#This is a command line testing script


use strict;
use warnings;

use Test::More tests => 1;
use Test::Cmd;
use JSON;


my $url = "http://140.221.84.191:7078/";
my $bin  = "scripts";

my $ws = "AKtest";
my $user = "aktest";
my $pw = "1475rokegi";
my $expression_data_series_id = "QuickTestExpressionDataSeries";


#1
my $tes = Test::Cmd->new(prog => "$bin/build_cmonkey_network_job_from_ws.pl", workdir => '', interpreter => '/kb/runtime/bin/perl');
ok($tes, "creating Test::Cmd object for build_cmonkey_network_job_from_ws");
$tes->run(args => "--url=$url --ws=$ws --input=$expression_data_series_id --nomotifs --nooperons --nonetworks --nostring --user=$user --pw=$pw");
ok($? == 0,"Running build_cmonkey_network_job_from_ws");
my $tem=$tes->stdout;
print "Job ID:\t",$tem,"\n";
ok($tem =~ /[1-9]+/, "Cmonkey runs OK");

