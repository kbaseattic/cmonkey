package us.kbase.cmonkey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;


class StreamGobbler extends Thread
{
    InputStream is;
    String type;
    String jobId = null;
    String logfileName = null;
    private static Pattern iterationPattern = Pattern.compile("INFO     Iteration # (\\d{1,2})(0{2,3})$");
    private static Pattern iterationNumberPattern = Pattern.compile("INFO     Iteration # ");
    private String token;
        
    StreamGobbler(InputStream is, String type, String logfileName)
    {
        this.is = is;
        this.type = type;
        this.logfileName = logfileName;
    }

    StreamGobbler(InputStream is, String type, String jobId, String token, String logfileName)
    {
        this.is = is;
        this.type = type;
        this.jobId = jobId;
        this.token = token;
        this.logfileName = logfileName;
    }

    public void run()
    {
        try
        {
        	InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            BufferedWriter writer = null;
            if (logfileName != null) {
            	writer = new BufferedWriter(new FileWriter(logfileName));
            };
            String line=null;
            while ( (line = br.readLine()) != null) {
            	if (writer != null) writer.write(line);
            	//System.out.println(type + ">" + line);
            	//Catch "INFO     Iteration # " pattern
           		if (iterationPattern.matcher(line).find()){
            		//call CmonkeyServerImpl
           			String[] lineParts = iterationNumberPattern.split(line);
           			System.out.println("ITERATION: " + lineParts[lineParts.length - 1]);
               		if (jobId != null) {
               			String status = "cMonkey iteration " + lineParts[lineParts.length - 1];
               			System.out.println(status);
            			CmonkeyServerImpl.updateJobProgress(jobId, status, token);
            		}
            	}
           		if (writer != null) writer.close();
            }
        } catch (IOException ioe)
        {
        	ioe.printStackTrace();  
        }
    }
}