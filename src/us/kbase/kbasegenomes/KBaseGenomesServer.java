package us.kbase.kbasegenomes;

import us.kbase.common.service.JsonServerServlet;

//BEGIN_HEADER
//END_HEADER

/**
 * <p>Original spec-file module name: KBaseGenomes</p>
 * <pre>
 * @author chenry,kkeller
 * </pre>
 */
public class KBaseGenomesServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;

    //BEGIN_CLASS_HEADER
    //END_CLASS_HEADER

    public KBaseGenomesServer() throws Exception {
        super("KBaseGenomes");
        //BEGIN_CONSTRUCTOR
        //END_CONSTRUCTOR
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: <program> <server_port>");
            return;
        }
        new KBaseGenomesServer().startupServer(Integer.parseInt(args[0]));
    }
}
