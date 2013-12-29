package us.kbase.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.zip.GZIPOutputStream;

import us.kbase.auth.TokenFormatException;
import us.kbase.common.service.JsonClientException;
import us.kbase.networks.Interaction;
import us.kbase.networks.InteractionSet;

public class NetworkExporter {

	private static final String PREFIX_OPERONS = "gnc";
	private static final String POSTFIX_OPERONS = ".named";

	public static void exportOperons(String setRef, String ncbiId,
			String workDir, String token) throws TokenFormatException,
			IOException, JsonClientException {
		InteractionSet set = WsDeluxeUtil.getObjectFromWsByRef(setRef, token)
				.getData().asClassInstance(InteractionSet.class);
		if (set != null) {
			DecimalFormat df = new DecimalFormat("0.000");
			BufferedWriter writer = new BufferedWriter(new FileWriter(workDir
					+ PREFIX_OPERONS + ncbiId + POSTFIX_OPERONS));
			writer.write("Gene1	Gene2	SysName1	SysName2	Name1	Name2	bOp	pOp	Sep	MOGScore	GOScore	COGSim	ExprSim\n");

			for (Interaction interaction : set.getInteractions()) {
				writer.write(interaction.getEntity1Id() + "\t"
						+ interaction.getEntity2Id() + "\t"
						+ interaction.getEntity1Id() + "\t"
						+ interaction.getEntity2Id() + "\t"
						+ interaction.getEntity1Id() + "\t"
						+ interaction.getEntity2Id() + "\t");

				if (interaction.getScores().get("SAME_OPERON")
						.equals(Double.valueOf("1.0"))) {
					writer.write("TRUE\t");
				} else if (interaction.getScores().get("SAME_OPERON")
						.equals(Double.valueOf("0.0"))) {
					writer.write("FALSE\t");
				} else {
					writer.write("\t");
				}

				if (interaction.getScores().containsKey("SAME_OPERON_SCORE")) {
					writer.write("\t"
							+ df.format(interaction.getScores().get(
									"SAME_OPERON_SCORE")));
				} else {
					writer.write("\t");
				}

				if (interaction.getScores().containsKey("GENE_DISTANCE")) {
					writer.write("\t"
							+ df.format(interaction.getScores().get(
									"GENE_DISTANCE")));
				} else {
					writer.write("\t");
				}

				if (interaction.getScores().containsKey("CONSERVATION_SCORE")) {
					writer.write("\t"
							+ df.format(interaction.getScores().get(
									"CONSERVATION_SCORE")));
				} else {
					writer.write("\t");
				}

				if (interaction.getScores().containsKey("GO_SCORE")) {
					writer.write("\t"
							+ df.format(interaction.getScores().get("GO_SCORE")));
				} else {
					writer.write("\tNA");
				}

				if (interaction.getScores().containsKey("COG_SIM")) {

					if (interaction.getScores().get("COG_SIM")
							.equals(Double.valueOf("1.0"))) {
						writer.write("\tY");
					} else if (interaction.getScores().get("COG_SIM")
							.equals(Double.valueOf("0.0"))) {
						writer.write("\tN");
					}
				} else {
					writer.write("\t");
				}

				if (interaction.getScores().containsKey("EXPR_SIM")) {
					writer.write("\t"
							+ df.format(interaction.getScores().get("EXPR_SIM"))
							+ "\n");
				} else {
					writer.write("\tNA" + "\n");
				}
			}
			set = null;
			if (writer != null)
				writer.close();
		}
	}

	public static void exportString(String setRef, String ncbiId,
			String workDir, String token) throws TokenFormatException,
			IOException, JsonClientException {
		InteractionSet set = WsDeluxeUtil.getObjectFromWsByRef(setRef, token)
				.getData().asClassInstance(InteractionSet.class);
		System.out.println("STRING network downloaded");
		if (set != null) {
			OutputStreamWriter writer = null;
			DecimalFormat df = new DecimalFormat("###");
			writer = new OutputStreamWriter(new GZIPOutputStream(
					new BufferedOutputStream(new FileOutputStream(workDir
							+ ncbiId + ".gz"))));
			for (Interaction interaction : set.getInteractions()) {
/*				System.out.println(interaction.getEntity1Id() + "\t"
						+ interaction.getEntity2Id() + "\t"
						+ interaction.getScores().get("STRING_SCORE"));
*/
				writer.write(interaction.getEntity1Id()
						+ "\t"
						+ interaction.getEntity2Id()
						+ "\t"
						+ df.format(interaction.getScores().get("STRING_SCORE"))
						+ "\n");
				writer.flush();
			}
			set = null;
			if (writer != null)
				writer.close();
		}
	}
}
