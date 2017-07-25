import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Implementation {

	// validation of kmeans clustering
	public double validate_kmeans(int numCluster, List<Cluster_Point> cluster,
			double SumSqEr, Cluster_Point centers[]) {
		double total_dist;
		double Squaered_dist_X;
		int KmeansClusterNo = numCluster;
		double Squaered_dist_Y;

		double Square_of_sum[] = new double[centers.length];
		// calculating SSE
		int i = 0;
		while(i < KmeansClusterNo)
		 {
			for (Cluster_Point p : cluster) {
				if (p.id_of_cluster == (i + 1)) {
					Squaered_dist_Y = Math
							.pow((p.point_y_Coordinate - centers[i].point_y_Coordinate),
									2);
					Squaered_dist_X = Math.pow((p.point_x_Coordinate - centers[i].point_x_Coordinate),
									2);

					total_dist = Math.sqrt((Squaered_dist_X + Squaered_dist_Y));
					Square_of_sum[i] = Math.pow(total_dist, 2)
							+ Square_of_sum[i];
				}
			}i++;
		}   
		for (double sSq : Square_of_sum) {
			SumSqEr = SumSqEr + sSq;
		}
		return SumSqEr;
	}// End of validate_kmeans method

	// Method initializing cluster centroid randomly
	public Cluster_Point[] Centroid_Generate(String id[], double array1[],
			double array2[], int numberOfClusters) {

		Cluster_Point gen_centroid[] = new Cluster_Point[numberOfClusters];
		Cluster_Point cent_point = new Cluster_Point();
		String ID_point;
		int randomNumber = 0;
		
		for (int num = 0; num < numberOfClusters;) {
			randomNumber = new Random().nextInt((id.length - 1) + 1);
			if (randomNumber >= 100 || randomNumber < 0) {
				num--;
				continue;
			} else {
				ID_point = id[randomNumber];
				
				gen_centroid[num] = cent_point.get_point_Initial(ID_point,
						array1, array2);
				gen_centroid[num].id_of_cluster = num + 1;
				num++;
			}
		}
		return gen_centroid;
	}// End of Centroid_Generate function

	// to compute cluster centroid
	public List<Cluster_Point> decide_Centroid(List<Cluster_Point> cluster,
			int clusterLen) {

		List<Cluster_Point> verify_Pt = new ArrayList<Cluster_Point>();
		Cluster_Point centroid[] = new Cluster_Point[clusterLen];

		Cluster_Point center_Pt = new Cluster_Point();

		double sumOf_YCoordinates[] = new double[clusterLen];
		double sumOf_XCoordinates[] = new double[clusterLen];

		double count[] = new double[clusterLen];
		int length = 1;

		while (clusterLen > 0) {
			for (Cluster_Point cl : cluster) {
				if (cl.id_of_cluster == length) {
					sumOf_YCoordinates[length - 1] += center_Pt
							.point_y_Coordinate(cl);
					sumOf_XCoordinates[length - 1] += center_Pt
							.point_x_Coordinate(cl);
					count[length - 1]++;
				}
			}
			length++;
			clusterLen--;

		}// while ends
		int counter_temp = 0;
		for (Cluster_Point cl : centroid) {
			cl = center_Pt.get_point_New(sumOf_XCoordinates[counter_temp]
					/ count[counter_temp], sumOf_YCoordinates[counter_temp]
					/ count[counter_temp], counter_temp + 1);
			verify_Pt.add(cl);
			counter_temp++;
		}
		return verify_Pt;
	}// function decide_Centroid ends

	// Calculating Euclidean distance and assign points to least distant
	// clusters
	public List<Cluster_Point> calc_EuclideanDist(Cluster_Point centroids[],
			double array1[], double array2[], Cluster_Point updatePoint) {

		Cluster_Point dist_center[] = centroids;
		Cluster_Point kmean_cluster = new Cluster_Point();
		double minimum_Distance[][] = new double[array1.length][array2.length];
		double euclideanDistance = 0;
		List<Cluster_Point> list = new ArrayList<Cluster_Point>();
		int index = 0;
		// find min distance
		int i = 0;
		while (i < dist_center.length) {
			int j = 0;
			while (j < array1.length) {
				double min_x = Math
						.sqrt(Math.pow(
								Math.abs(dist_center[i].point_x_Coordinate
										- array1[j]), 2));
				double min_y = Math
						.pow(Math.abs(dist_center[i].point_y_Coordinate
								- array2[j]), 2);
				minimum_Distance[i][j] = min_x + min_y;
				j++;
			}
			i++;
		}
		int i1 = 0;
		while (i1 < array1.length) {
			List<Double> temp_list = new ArrayList<Double>();
			for (int j = 0; j < dist_center.length; j++) {
				temp_list.add(minimum_Distance[j][i1]);
			}
			euclideanDistance = Collections.min(temp_list);
			index = temp_list.indexOf(euclideanDistance);
			updatePoint = kmean_cluster.setPoint(array1[i1], array2[i1],
					index + 1, i1 + 1);
			list.add(updatePoint);
			i1++;
			// return list with updated points
		}
		return list;
	}// function calc_EuclideanDist ends

}
// to compute the new centroids based on the points assigned to the cluster

// to evaluate the goodness of clustering using sum of squared error