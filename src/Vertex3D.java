import java.awt.*;

/**
 * The department handles the vertex - a point in the three-dimensional space
*/
public class Vertex3D 
{
		private double x; 
		private double y; 
		private double z; 
		
/**
* Constructive action to create a vertex
* @param x - the point's x value
* @param y - the point's y value
* @param z - the point's z value
*/
		public Vertex3D(double x,double y,double z)
		{
			this.x=x;
			this.y=y;
			this.z=z;
		}
		
/**
* A constructive action is copied
* @param k - vertex based on whose values ​​the new vertex is created
*/
		public Vertex3D(Vertex3D k)
		{
			this.x=k.x;
			this.y=k.y;
			this.z=k.z;
		}
/**
* An operation that returns the  value of the vertex
* @return the x Value of the vertex
*/
		public double getX()
		{
			return this.x; 
		}
		
	/**
* An operation that returns the y value of the vertex
* @return the y value of the vertex
*/
		public double getY()
		{
			return this.y; 
		}
		
		/**
		* An operation that returns the Z value of the vertex
* @return the Z value of the vertex
		 */	
		public double getZ()
		{
			return this.z; 
		}
		
		/**
		* of the vertex x an operation that changes the value
		* @param x new value of
		*/
		public void setX(double x)
		{
			this.x=x;
		}
		
		/**
		* of the vertex Y an operation that changes the value
		* @param Y new value of
		*/
			public void setY(double y)
		{
			this.y=y;
		}

		/**
		* of the vertex Z an operation that changes the value
		* @param Z new value of
		*/	
		public void setZ(double z)
		{
			this.z=z;
		}

		/**
        * The operation returns a parallel projection point of the vertex
		* @return parallel projection point of the vertex
		*/
		public Point parallelProj()
	    {
	       return new Point((int)(this.x+this.z*(0.5*Math.cos(Math.PI/6))),
	    		            (int)(this.y+this.z*(0.5*Math.sin(Math.PI/6))));
	       
	    }
	    
		/**
		 * The operation returns a perspective projection point of the vertex
		 * @return perspective projection point of the vertex
		 */
		public Point perspectiveProj()
	    {
		   int d=-400;	
	       return new Point((int)(this.x*(1-(this.z/(this.z+d)))),
	    		            (int)(this.y*(1-(this.z/(this.z+d)))));
	    }  

		/*The operation returns a homogeneous point which is the transformation of the vertex
		*
		* @return [x,y,z,1] a homogeneous point from the shape
		*/
		public double[] verToHomogeni()
	    {
	    	double[] homogeni=new double[4];
	    	homogeni[0]=this.x;
	    	homogeni[1]=this.y;
	    	homogeni[2]=this.z;
	    	homogeni[3]=1;
	    	return homogeni;	    }
	    
		/** The operation returns a vertex which is a transformation of a homogeneous point received as a parameter
		*
		* @param h - [x,y,z,1] homogeneous point from the shape
		* @return a vertex that is the homogeneous point transform
		*/
		public Vertex3D homogeniToVer(double[] h)
	    {
			this.setX(h[0]);
			this.setY(h[1]);
			this.setZ(h[2]);
	    	return this;
	    }
		
		/**
		* Connecting 2 vertices
		* @param v connected
		* @return sum of vertices
		*/
		public Vertex3D add(Vertex3D v)
		{
			return new Vertex3D(this.x+v.x,this.y+v.y,this.z+v.z);
		}
		
		/**
		* The operation compares the values ​​of two points
		* @param v the second point
		* @return true if their values ​​are identical, otherwise false
		*/
		public boolean equals(Vertex3D v)
		{
			return (this.x==v.x)&&(this.y==v.y)&&(this.z==v.z); 
		}
		
		/**
		* The operation returns the distance between two points
		* @param v second point
		* @return the distance between two points in space
		*/
		public double distance(Vertex3D v)
		{
			return Math.sqrt(Math.pow(this.x-v.x,2)+Math.pow(this.y-v.y,2)+Math.pow(this.z-v.z,2));
		}
		
		/*** The operation checks whether the current point is before the point received as a parameter
		* z,x,y The comparison is done by checking values
		* @param v the second vertex
		* @return whether the first vertex is before the second vertex
		 */
		public boolean before(Vertex3D v)
		{
			if (v.z>this.z)
				return true;
			else if ((v.z==this.z) && (v.x>this.x))
			     return true;
			     else if ((v.z==this.z) && (v.x==this.x)&& (v.y>this.y))
			     return true;
			return false;
		}	
			     
		/**
		 * An operation that returns a string describing the 3D vertex
		 */
		public String toString()
		{
			return "("+this.x+","+this.y+","+this.z+")";
		}
}
