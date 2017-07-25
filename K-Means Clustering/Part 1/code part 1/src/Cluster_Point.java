
public class Cluster_Point {
	int id_of_cluster=0;
	double point_x_Coordinate=0;
	double point_y_Coordinate=0;
	int ID_p=0;
	public Cluster_Point setPoint(double array1,double array2,int cId,int id){
		Cluster_Point pt = new Cluster_Point();
		//create object of cluster_point
		pt.point_x_Coordinate = array1;
		pt.point_y_Coordinate = array2;
		pt.id_of_cluster=cId;
		pt.ID_p = id;
		return pt;
	}
	
	public double point_x_Coordinate(Cluster_Point pt){
		return pt.point_x_Coordinate;
	}

	public Cluster_Point get_point_Initial(String pID,double array1[],double array2[]){
		Cluster_Point pt = new Cluster_Point();
		pt.ID_p = Integer.parseInt(pID);
		pt.point_x_Coordinate = array1[pt.ID_p];
		pt.point_y_Coordinate = array2[pt.ID_p];
		return pt;
	}
		
	public Cluster_Point get_point_New(double xCoordinate,double yCoordinate,int cId){
		Cluster_Point pt = new Cluster_Point();
		pt.point_x_Coordinate = xCoordinate;
		pt.point_y_Coordinate = yCoordinate;
		pt.id_of_cluster = cId;
		return pt;
	}
	
	public double point_y_Coordinate(Cluster_Point pt){
		return pt.point_y_Coordinate;
	}

}
