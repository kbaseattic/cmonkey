use Bio::KBase::cmonkey::cmonkeyImpl;

use Bio::KBase::cmonkey::Service;
use Plack::Middleware::CrossOrigin;



my @dispatch;

{
    my $obj = Bio::KBase::cmonkey::cmonkeyImpl->new;
    push(@dispatch, 'Cmonkey' => $obj);
}


my $server = Bio::KBase::cmonkey::Service->new(instance_dispatch => { @dispatch },
				allow_get => 0,
			       );

my $handler = sub { $server->handle_input(@_) };

$handler = Plack::Middleware::CrossOrigin->wrap( $handler, origins => "*", headers => "*");
