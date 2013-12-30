package us.kbase.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import us.kbase.auth.TokenFormatException;
import us.kbase.common.service.JsonClientException;
import us.kbase.kbasegenomes.Contig;
import us.kbase.kbasegenomes.ContigSet;
import us.kbase.kbasegenomes.Feature;
import us.kbase.kbasegenomes.Genome;

public class GenomeExporter {

	private static final String FEATURES = "_features";
	private static final String FEATURENAMES = "_feature_names";

	public static String writeGenome (String genomeRef, String filePrefix, String workDir, String token) throws TokenFormatException, IOException, JsonClientException{
		Genome genome = WsDeluxeUtil.getObjectFromWsByRef(genomeRef, token).getData().asClassInstance(Genome.class);
		String returnVal = null;
		if (genome != null) {
			returnVal = genome.getScientificName();
			//Write contigs
			writeContigFiles(genome.getContigsetRef(), filePrefix, workDir, token);
			//Write features
			List<Feature> features = genome.getFeatures();
			writeFeaturesFile(features, genome, filePrefix, workDir);
			//Write feature names
			writeFeatureNamesFile(features, filePrefix, workDir);
			genome = null;
		} else {
			System.out.println("Genome object not found");
		}
		return returnVal;
	}
	
	public static void writeContigFiles(String contigSetRef, String filePrefix, String workDir, String token) throws TokenFormatException, IOException, JsonClientException {
		ContigSet contigSet = WsDeluxeUtil.getObjectFromWsByRef(contigSetRef, token).getData().asClassInstance(ContigSet.class);
		for (Contig contig : contigSet.getContigs()){
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(workDir + filePrefix + "_" + contig.getName()));
				if (contig.getSequence() != null){
					writer.write(contig.getSequence());
				}
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			} finally {
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
				}
			}
		}
	}
	
	public static void writeFeaturesFile(List<Feature> features, Genome genome, String filePrefix, String workDir){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(workDir + filePrefix + FEATURES));
			writer.write("-- dump date   	20121212_172621\n-- class       	Genbank::Feature\n-- table       	feature\n-- table       	main\n" +
					"-- field 1	id\n-- field 2	type\n-- field 3	name\n-- field 4	contig\n-- field 5	start_pos\n-- field 6	end_pos\n-- field 7	strand\n-- field 8	description\n" + 
					"-- field 9	chrom_position\n-- field 10	organism\n-- field 11	GeneID\n-- header\n-- id	type	name	contig	start_pos	end_pos	strand	description	chrom_position	organism	GeneID\n");
			
			for (Feature feature: features){
				
				writer.write(feature.getId());
				String featureType = feature.getType();
				if (featureType.equals("peg")) featureType = "CDS"; 
				writer.write("\t" + featureType); //type
/*				if (feature.getAliases().size() > 1) {
					writer.write("\t" + feature.getAliases().get(1)); //id
				} else {
*/					writer.write("\t" + feature.getId()); //or primary name
//				}
				writer.write("\t" + feature.getLocation().get(0).getE1()); //contig
				writer.write("\t" + feature.getLocation().get(0).getE3().toString()); //start position
				Long endPos = feature.getLocation().get(0).getE3() + feature.getLocation().get(0).getE5() - 1;
				writer.write("\t" + endPos.toString()); //end pos
				writer.write("\t" + feature.getLocation().get(0).getE4());//strand
				writer.write("\t" + feature.getFunction()); //description
				if (feature.getLocation().get(0).getE4().equals("R")){
					writer.write("\tcomplement(" + feature.getLocation().get(0).getE3().toString() + ".." + endPos.toString() + ")");
				} else {
					writer.write("\t" + feature.getLocation().get(0).getE3().toString() + ".." + endPos.toString());
				}
				writer.write("\t" + genome.getScientificName());//organism
				if (feature.getAliases().size() > 2) {
					writer.write("\t" + feature.getAliases().get(2)); //gene name
				} else {
					writer.write(feature.getId());
				}
				writer.write("\n");
			}
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}

	
	public static void writeFeatureNamesFile(List<Feature> features, String filePrefix, String workDir){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(workDir + filePrefix + FEATURENAMES));
			writer.write("-- class       	Genbank::Feature\n-- table       	feature_names\n-- id	names");
			for (Feature feature: features){
//				writer.write("\n" + feature.getId() + "\t" + feature.getAliases().get(1) + "\tprimary");
				writer.write("\n" + feature.getId() + "\t" + feature.getId() + "\tprimary");
				writer.write("\n" + feature.getId() + "\t" + feature.getAliases().get(1) + "\talternate");
				writer.write("\n" + feature.getId() + "\t" + feature.getAliases().get(0) + "\talternate");
				for (int i = 2; i < feature.getAliases().size(); i++){
					writer.write("\n" + feature.getId() + "\t" + feature.getAliases().get(i) + "\talternate");
				}
			}
			writer.write("\n");
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}	
	
}
