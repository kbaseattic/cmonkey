use strict;
use Data::Dumper;
use Carp;

=head1 NAME

    build_cmonkey_network_job_from_ws - build bi-cluster network starting with expression data series stored in workspace

=head1 SYNOPSIS

    build_cmonkey_network_job_from_ws [--url=http://140.221.84.191:7078/ --ws=<workspace ID> --input=<expression data series ID> --nomotifs --nooperons --nonetworks --nostring --user=<username> --pw=<password>]

=head1 DESCRIPTION

    Discovers co-regulated modules, or biclusters in gene expression profiles.

=head2 Documentation for underlying call

    Returns Job object ID that keeps ID of CmonkeyRunResult object stored in workspace.

=head1 OPTIONS

=over 6

=item B<--url>=I<http://140.221.84.191:7078/>
    the service url

=item B<-h> B<--help>
    print help information

=item B<--version>
    print version information

=item B<--ws>
    workspace ID

=item B<--input>
    KBase ID of the expression data series

=item B<--nomotifs>
    Motif scoring will not be used

=item B<--nooperons>
    MicrobesOnline operons data will not be used

=item B<--nonetworks>
    Network scoring will not be used

=item B<--nostring>
    STRING data will not be used

=item B<--user>
    User name for access to workspace

=item B<--pw>
    Password for access to workspace

=back

=head1 EXAMPLE

    build_cmonkey_network_job_from_ws --url=http://140.221.84.191:7078/ --ws=AKtest --input=QuickTestExpressionDataSeries --nomotifs --nooperons --nonetworks --nostring --user=<username> --pw=<password>
    build_cmonkey_network_job_from_ws --help
    build_cmonkey_network_job_from_ws --version

=head1 VERSION

    1.0

=cut

use Getopt::Long;
use Bio::KBase::cmonkey::Client;
use Bio::KBase::AuthToken;
use Bio::KBase::AuthUser;

my $usage = "Usage: build_cmonkey_network_job_from_ws [--url=http://140.221.84.191:7078/ --ws=<workspace ID> --input=<sequence set ID> --nomotifs --nooperons --nonetworks --nostring --user=<username> --pw=<password>]\n";

my $url        = "http://140.221.84.191:7078/";
my $input      = "";
my $ws		   = "";
my $nomotifs   = 0;
my $nooperons  = 0;
my $nonetworks = 0;
my $nostring   = 0;
my $user       = "";
my $pw         = "";
my $help       = 0;
my $version    = 0;

GetOptions("help"       => \$help,
           "version"    => \$version,
           "ws=s"    => \$ws,
           "input=s"    => \$input,
           "nomotifs:i"    => \$nomotifs,
           "nooperons:i"    => \$nooperons,
           "nonetworks:i"    => \$nonetworks,
           "nostring:i"    => \$nostring,
           "user=s"    => \$user,
           "pw=s"    => \$pw,
           "url=s"     => \$url) 
           or exit(1);

if($help){
print "NAME\n";
print "build_cmonkey_network_job_from_ws - This command discovers co-regulated modules, or biclusters in gene expression profiles stored in workspace.\n";
print "\n";
print "\n";
print "VERSION\n";
print "1.0\n";
print "\n";
print "SYNOPSIS\n";
print "build_cmonkey_network_job_from_ws [--url=http://140.221.84.191:7078/ --ws=<workspace ID> --input=<sequence set ID> --nomotifs --nooperons --nonetworks --nostring --user=<username> --pw=<password>]\n";
print "\n";
print "DESCRIPTION\n";
print "INPUT:            This command requires the URL of the service, workspace name, ID of expression data series and run parameters.\n";
print "\n";
print "OUTPUT:           This command returns Job object ID.\n";
print "\n";
print "PARAMETERS:\n";
print "--url             The URL of the service, --url=http://140.221.84.191:7078/, required.\n";
print "\n";
print "--ws              Workspace ID, required.\n";
print "\n";
print "--input           KBase ID of the expression dara series, required.\n";
print "\n";
print "--nomotifs        Motif scoring will not be used.\n";
print "\n";
print "--nonetworks      Network scoring will not be used.\n";
print "\n";
print "--nooperons       MicrobesOnline operons data will not be used.\n";
print "\n";
print "--nostring        STRING data will not be used.\n";
print "\n";
print "--user            User name for access to workspace.\n";
print "\n";
print "--pw              Password for access to workspace.\n";
print "\n";
print "--help            Display help message to standard out and exit with error code zero; \n";
print "                  ignore all other command-line arguments.  \n";
print "--version         Print version information. \n";
print "\n";
print " \n";
print "EXAMPLES \n";
print "build_cmonkey_network_job_from_ws --url=http://140.221.84.191:7078/ --ws=AKtest --input=QuickTestExpressionDataSeries --nomotifs --nooperons --nonetworks --nostring --user=<username> --pw=<password> \n";
print "\n";
print "This command will return a Job object ID.\n";
print "\n";
print "\n";
print "Report bugs to aekazakov\@lbl.gov\n";
exit(0);
};

if($version)
{
    print "build_cmonkey_network_job_from_ws\n";
    print "Copyright (C) 2013 DOE Systems Biology Knowledgebase\n";
    print "License GPLv3+: GNU GPL version 3 or later <http://gnu.org/licenses/gpl.html>.\n";
    print "This is free software: you are free to change and redistribute it.\n";
    print "There is NO WARRANTY, to the extent permitted by law.\n";
    print "\n";
    print "Report bugs to aekazakov\@lbl.gov\n";
    exit(0);
};

unless (@ARGV == 0){
    print $usage;
    exit(1);
};

my $auth_user = Bio::KBase::AuthUser->new();
my $token = Bio::KBase::AuthToken->new( user_id => $user, password => $pw);
$auth_user->get( token => $token->token );

if ($token->error_message){
	print $token->error_message."\n\n";
	exit(1);
};

my $cmonkey_run_parameters = {
    "nomotifs"=>$nomotifs,
    "nooperon"=>$nooperon,
    "nonetworks"=>$nonetworks
};

my $obj = {
	method => "Cmonkey.build_cmonkey_network_job_from_ws",
	params => [$ws, $input, $cmonkey_run_parameters],
};

my $client = Bio::KBase::cmonkey::Client::RpcClient->new;
$client->{token} = $token->token;

my $result = $client->call($url, $obj);

my @keys = keys % { $result };

if (${$result}{is_success} == 1){
	my $result_id = ${$result}{jsontext};
	$result_id =~ s/\"\]\}$//;
	$result_id =~ s/^.*\"\,\"result\"\:\[\"//;
	print $result_id."\n\n";
	exit(0);
}
else {
	print ${$result}{jsontext}."\n";
	exit(1);
}
exit(1);

