#!/usr/bin/perl

#This is a command line testing script


use strict;
use warnings;

use Test::More tests => 3;
use Test::Cmd;
use JSON;


my $url = "\"http://localhost:7112/\"";
my $bin  = "scripts";

my $ws = "\"AKtest\"";
my $user = "aktest";
my $pw = "1475rokegi";
my $series_ref = "\"AKtest/test_Halobacterium_sp_expression_series\"";
my $genome_ref = "\"AKtest/Halobacterium_sp_NRC-1\"";


#1
my $tes = Test::Cmd->new(prog => "$bin/run_cmonkey.pl", workdir => '', interpreter => '/kb/runtime/bin/perl');
ok($tes, "creating Test::Cmd object for run_cmonkey");
$tes->run(args => "--url=$url --ws=$ws --input=$series_ref --genome=$genome_ref --user=$user --pw=$pw");
ok($? == 0,"Running cmonkey");
my $tem=$tes->stdout;
print "Job ID:\t",$tem,"\n";
ok($tem =~ /[1-9]+/, "Cmonkey runs OK");

