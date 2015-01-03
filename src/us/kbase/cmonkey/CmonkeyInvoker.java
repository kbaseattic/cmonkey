package us.kbase.cmonkey;

import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class CmonkeyInvoker {

	/**
	 * @param args
	 */
	
	Options options = new Options();
	final static Pattern p = Pattern.compile("^'(.*)'$");

	@SuppressWarnings("static-access")
	public CmonkeyInvoker() {

		options.addOption( OptionBuilder.withLongOpt( "help" )
                .withDescription( "print this message" )
                .withArgName("help")
                .create() );

		options.addOption( OptionBuilder.withLongOpt( "test" )
                .withDescription( "for testing only" )
                .withArgName("test")
                .create() );

		options.addOption( OptionBuilder.withLongOpt( "method" )
                .withDescription( "available methods: build_cmonkey_network_job_from_ws" )
                .hasArg(true)
                .withArgName("NAME")
                .create() );
		
		options.addOption( OptionBuilder.withLongOpt( "job" )
                .withDescription( "job ID" )
                .hasArg(true)
                .withArgName("job")
                .create() );

		options.addOption( OptionBuilder.withLongOpt( "ws" )
                .withDescription( "workspace name where run result will be stored" )
                .hasArg(true)
                .withArgName("workspace_id")
                .create() );
		
		options.addOption( OptionBuilder.withLongOpt( "series" )
                .withDescription( "expression data series WS reference" )
                .hasArg(true)
                .withArgName("series")
                .create() );

		options.addOption( OptionBuilder.withLongOpt( "genome" )
                .withDescription( "genome WS reference" )
                .hasArg(true)
                .withArgName("genome")
                .create() );

		options.addOption( OptionBuilder.withLongOpt( "motifs" )
                .withDescription( "Motif scoring will be used: 0|1" )
                .hasArg(true)
                .withArgName("motifs")
                .create() );

		options.addOption( OptionBuilder.withLongOpt( "networks" )
                .withDescription( "Network scoring will be used: 0|1" )
                .hasArg(true)
                .withArgName("networks")
                .create() );

		options.addOption( OptionBuilder.withLongOpt( "operons" )
                .withDescription( "Operon data source WS reference" )
                .hasArg(true)
                .withArgName("operons")
                .create() );

		options.addOption( OptionBuilder.withLongOpt( "string" )
                .withDescription( "Network data source WS reference" )
                .hasArg(true)
                .withArgName("string")
                .create() );

		options.addOption( OptionBuilder.withLongOpt( "token" )
                .withDescription( "Authorization token" )
                .hasArg(true)
                .withArgName("token")
                .create() );

	}

	private void runCmonkey (CommandLine line) throws Exception{

		CmonkeyRunParameters params = new CmonkeyRunParameters();		    			
		
		if ( line.hasOption("motifs")){
			params.setMotifsScoring(Long.parseLong(line.getOptionValue("motifs")));
		}
		else {
			System.err.println( "Required motifs parameter missed");
			System.exit(1);
		}
		
		if ( line.hasOption("networks")){
			params.setNetworksScoring(Long.parseLong(line.getOptionValue("networks")));
		}
		else {
			System.err.println( "Required networks parameter missed");
			System.exit(1);
		}

		if ( line.hasOption("operons")){
			if (!(cleanUpArgument(line.getOptionValue("operons")).equals("null"))) {
				params.setOperomeRef(cleanUpArgument(line.getOptionValue("operons")));
			}
		}
		else {
			System.err.println( "Required operons parameter missed");
			System.exit(1);
		}

		if ( line.hasOption("string")){
			if (!(cleanUpArgument(line.getOptionValue("string")).equals("null"))) {
				params.setNetworkRef(cleanUpArgument(line.getOptionValue("string")));
			}
		}
		else {
			System.err.println( "Required string parameter missed");
			System.exit(1);
		}
		String currentDir = System.getProperty("user.dir");
		System.out.println("Run cmonkey from "+currentDir);
		
		String wsId = cleanUpArgument(line.getOptionValue("ws"));
		System.out.println(wsId);		

		params.setSeriesRef(cleanUpArgument(line.getOptionValue("series")));
		System.out.println(params.getSeriesRef());		

		params.setGenomeRef(cleanUpArgument(line.getOptionValue("genome")));
		System.out.println(params.getGenomeRef());		

		String token = cleanUpArgument(line.getOptionValue("token"));
		System.out.println(token);

		CmonkeyServerImpl.buildCmonkeyNetworkJobFromWs(wsId, params, line.getOptionValue("job"), token, currentDir);
				
	}

	public void run (String[] args) throws Exception{

		String serverMethod = "";
		CommandLineParser parser = new GnuParser();

		try {
	        // parse the command line arguments
	        CommandLine line = parser.parse( options, args);
		    if( line.hasOption( "help" ) ) {
		    	// automatically generate the help statement
		    	HelpFormatter formatter = new HelpFormatter();
		    	formatter.printHelp( "java -jar /kb/deployment/cmonkey/cmonkey_cluster.jar [parameters]", options );

		    }
		    else if( line.hasOption( "test" ) ) {
		    	// return nothing and exit
		    	System.exit(0);
		    }
		    else {
		    	if ( validateInput(line)){
		    		serverMethod = line.getOptionValue( "method" );

		    		if (serverMethod.equalsIgnoreCase("build_cmonkey_network_job_from_ws")){
		    			runCmonkey(line);
		    		}
		    		else {
		    			System.err.println( "Unknown method: " + serverMethod + "\n");
				    	HelpFormatter formatter = new HelpFormatter();
				    	formatter.printHelp( "java -jar /kb/deployment/cmonkey/cmonkey_cluster.jar [parameters]", options );
		    			System.exit(1);
		    		}
 
		    	}
		    	else {
			    	HelpFormatter formatter = new HelpFormatter();
			    	formatter.printHelp( "java -jar /kb/deployment/cmonkey/cmonkey_cluster.jar [parameters]", options );
		    		System.exit(1);
		    	}
		    }
	        
	    }
	    catch( ParseException exp ) {
	        // oops, something went wrong
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	    }

	}

	
	private static boolean validateInput (CommandLine line) {
		boolean returnVal = false;
		if (line.hasOption("method")){

			if (line.hasOption("ws")){
				
				if (line.hasOption("token")){

					if (line.hasOption("series")){

						if (line.hasOption("job")){
							
							if (line.hasOption("genome")){

								returnVal = true;
							}
							else {
								System.err.println( "Genome ID required");
							}
						}
						else {
							System.err.println( "Job ID required");
						}
						
					} 
					else {
						System.err.println( "Expression data series ID required");
					}

				}
				else {
					System.err.println( "Authorization required");
				}
				
			}
			else {
				System.err.println( "Workspace ID required");
			}

		}
		else {
			System.err.println( "Method required");
		}
		return returnVal;
	}

	protected static String cleanUpArgument (String argument){
		if (argument.matches(p.pattern())){
			argument = argument.replaceFirst(p.pattern(), "$1");
		}
		return argument;
	}

	public static void main(String[] args) throws Exception {
		CmonkeyServerImpl.startup();
		CmonkeyInvoker invoker = new CmonkeyInvoker();
		invoker.run(args);
	}

	
}
