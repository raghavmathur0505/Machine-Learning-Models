
import java.io.*;
import java.util.*;


//Main class
public class MlClustering 
{	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//Cluster_Point cp =  new Cluster_Point();
		String KmeansClusterNo=args[0];
		int numberOfClusters = Integer.parseInt(KmeansClusterNo);
		//input no. of clusters from argument
		String file_IP = args[1];
		String file_OUT = args[2];
		//input input and output file names
		int Def_Iterations=1;
		
		
		
		File file=new File(file_IP);
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(file);
		int size=100;		
		double[] array1 = new double[size];
		double[] array2 = new double[size];
		String[] id = new String[size];
		int i = -1;

		while(scan.hasNextLine())
		{
			String nLines=scan.nextLine();
			if(i != -1)
			{	
				//splitting words in file on tab
				String[] split = nLines.split("\t");
				id[i] = split[0];
				array2[i] = Double.parseDouble(split[2]);
				array1[i] = Double.parseDouble(split[1]);
				
			}
			i++;
		}

		
		Cluster_Point kmeans_centroid[]=new Cluster_Point[numberOfClusters];	
		Implementation obj = new Implementation();
		
		List<Cluster_Point> clusterOfPoints=new ArrayList<Cluster_Point>();
		
		Cluster_Point var_Point =new Cluster_Point();
		
		kmeans_centroid=obj.Centroid_Generate(id,array1,array2,numberOfClusters);
		
		clusterOfPoints=obj.calc_EuclideanDist(kmeans_centroid,array1,array2,var_Point);
		
		Cluster_Point Centroid_New[];
		do{
			List<Cluster_Point> create_new_Cluster = new ArrayList<Cluster_Point>();
			Cluster_Point updateNewPoint = new Cluster_Point();
			List<Cluster_Point> update_kmeans_Centroid=obj.decide_Centroid(clusterOfPoints,kmeans_centroid.length);	
						
			Centroid_New=update_kmeans_Centroid.toArray(new Cluster_Point[update_kmeans_Centroid.size()]);			
			create_new_Cluster=obj.calc_EuclideanDist(Centroid_New,array1,array2,updateNewPoint);
			if(create_new_Cluster.equals(clusterOfPoints)){
				System.out.println("Ends here: and converges after" + Def_Iterations + "number of steps");
				break;
			}
			Def_Iterations++;
			clusterOfPoints=create_new_Cluster;
			
		}while(Def_Iterations<=25);		
		
		double SumSqEr = 0;
		//Implementation obj=new Implementation();
		SumSqEr = obj.validate_kmeans(numberOfClusters,clusterOfPoints,0,Centroid_New);

		
		try {
			//printing to a file
			PrintWriter Printer = new PrintWriter(new File(file_OUT));
			int len=0;
			while(len<kmeans_centroid.length)
			{			
				List<Integer> Cluster_Point = new ArrayList<Integer>();
				for(int size1=0; size1<= clusterOfPoints.size()-1 ;size1++){				
					if(clusterOfPoints.get(size1).id_of_cluster == len+1)	{

						Cluster_Point.add(clusterOfPoints.get(size1).ID_p);
					}	
				}			
				Printer.print(len+ 1 + "\t");
				int point_size = Cluster_Point.size() - 1;
				int num_count = 0;
				for(int point:Cluster_Point){
					if(num_count == point_size){
						Printer.print(point);
						num_count=num_count+1;
					}
					else{
						Printer.print(point + " , ");
						num_count=num_count+1;
					}
				}			
				Printer.println();	
				len++;
			}
			//the outermost loop ends
			
			Printer.println("Sum of Squared Error for clustering with [k = "+ numberOfClusters +"] : " + SumSqEr);

			Printer.close();	
				
		} catch (FileNotFoundException e) {
			System.out.println("Output file cannot be found/created");
		}
		
				
	}// End of main function
}// MlClustering class ends here

