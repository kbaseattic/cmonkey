#!/usr/bin/perl

# Script for testing back-end of meme service

use strict;
use warnings;

use Bio::KBase::AuthToken;
use Bio::KBase::AuthUser;
use Bio::KBase::userandjobstate::Client;

my $user = "aktest";
my $pw = "1475rokegi";

my $auth_user = Bio::KBase::AuthUser->new();
my $token = Bio::KBase::AuthToken->new( user_id => $user, password => $pw);
$auth_user->get( token => $token->token );

if ($token->error_message){
	print $token->error_message."\n\n";
	exit(1);
};

my $auth_token = $token->token;

my $job_client = Bio::KBase::userandjobstate::Client->new("https://kbase.us/services/userandjobstate", "user_id", $user, "password", $pw);

my $deployment_dir = "/kb/deployment/lib/jars/cmonkey/";

my $command_line = "java -jar ".$deployment_dir."cmonkey.jar";

my $ws = "AKtest";
my $series = "AKtest/test_Halobacterium__series";
my $genome = "AKtest/Desulfovibrio_vulgaris_Hildenborough";
my $string = "null";
my $operons = "null";
my $use_motifs = "0";
my $use_networks = "0";

my $test_command = "";


#1 help
$test_command = $command_line." --help";
print $test_command."\n\n";
system ($test_command);

#2 build_cmonkey_network_job_from_ws
my $job = $job_client->create_job();
$test_command = $command_line." --job $job --method build_cmonkey_network_job_from_ws --ws \"$ws\" --series \"$series\" --genome \"$genome\" --motifs $use_motifs --networks $use_networks --operons \"$operons\" --string \"$string\" --token \"$auth_token\"";
print $test_command."\n\n";
system ($test_command);

exit(0);
