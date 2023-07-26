import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Class Shape3D
 * Every three-dimensional shape is composed of an array of vertices and an array of polygons.
 */
public class Shape3D 
{
	protected Vertex3D[] vers; 
	protected Polygon3D[] surs; 
		
	/**
	 * constructor
	 * @param vers - vertex vector
	 * @param surs - polygon vector
	 */
	public Shape3D(Vertex3D[] vers,Polygon3D[] surs)
	{
		this.vers=new Vertex3D[vers.length];
		this.surs=new Polygon3D[surs.length];
		for(int i=0;i<vers.length;i++)
			this.vers[i]=vers[i];
		for(int i=0;i<surs.length;i++)
			this.surs[i]=surs[i];
	}
	
	/**
	 *copy constructor
	 * @param s - shape3D
	 */
	public Shape3D(Shape3D s)
	{
		this.vers=new Vertex3D[s.getVertex().length];
		this.surs=new Polygon3D[s.getSurface().length];
		for(int i=0;i<s.getVertex().length;i++)
			this.vers[i]=new Vertex3D(s.getVertex()[i]);
		
		Vertex3D[] temp;
		for(int i=0;i<s.getSurface().length;i++)
		{
			temp=new Vertex3D[s.getSurface()[i].getNum()];
			for(int j=0;j<s.getSurface()[i].getNum();j++)
			{
				for (int k=0;k<this.vers.length;k++)
				if (s.surs[i].vers[j].equals(this.vers[k]))
						temp[j]=this.vers[k];
			}
			this.surs[i]=new Polygon3D(temp,s.getSurface()[i].getColor());
		}
	}
	
	/**
	 * An operation that returns the number of codes in the three-dimensional body
	 * @return num of codes
	 */
    public int getNumVer()
    {
    	return this.vers.length;
    }
    
    /**
     * An operation that returns the arry of codes in the three-dimensional body
     * @return arry of cods
     */
    public Vertex3D[] getVertex()
    {
    	return this.vers;
    }
    
    /**
	 * An operation that returns the number of face in the three-dimensional body
	 * @return num of face
	 */
    public int getNumSur()
    {
    	return this.surs.length;
    }
    
    /**
     * An operation that returns the polygon arry
     * @return polygon arry
     */
    public Polygon3D[] getSurface()
    {
    	return this.surs;
    }
    
    /*** An operation that replaces the array of vertices with a new array
     * @param vers - the new vector
     */
    public void setVertex(Vertex3D[] vers)
    {
    	for(int i=0;i<vers.length;i++)
			this.vers[i]=vers[i];
    }
    
    /**
 * An operation that returns an array of polygons in a plane,
     * each of which is a parallel projection of a 3D polygon in the 3D body
     * @return the array of toss polygons
     */
    public Polygon[] parallelShape()
    {
    	int numM=this.surs.length;
    	Polygon poly[]=new Polygon[numM]; // מספר פאות הקוביה
    	for (int i=0;i<numM;i++)
    	{	
    		poly[i]=this.surs[i].parallelPoly();
    	}	
    	return poly;
    }
    
    /**
     * An operation that returns an array of polygons in a plane,
     * each of which is a perceptive projection of a 3D polygon on a 3D body
     * @return the array of toss polygons
     */
    public Polygon[] perspectiveShape()
    {
    	int numM=this.surs.length;
    	Polygon poly[]=new Polygon[numM]; // מספר פאות הקוביה
    	for (int i=0;i<numM;i++)
    	{	
    		poly[i]=this.surs[i].perspectivePoly();
    	}	
    	return poly;
    }

    /**
     * An operation that receives a transformation matrix, and changes the three-dimensional body accordingly
     * @param tran - transformation matrix
     */
    public void change(Tran3D tran)
    {
    	int numK=this.vers.length;
    	double[] vec=new double[4];
    	double[] vec1=new double[4];
    	for(int i=0;i<numK;i++)
    	{
    		vec=this.vers[i].verToHomogeni();
      		vec1=tran.mullVecMat(vec);
    		this.vers[i]=this.vers[i].homogeniToVer(vec1);
      	}
    }
    
    
   /**
    * An operation that checks whether the vertex is contained within the 3D body
    * @param the 3D vertex being tested
    * @return true - if the point is contained in a polygon
    */
    public boolean inShape(Vertex3D v)
    {
    	for(int i=0;i<this.surs.length;i++)
    	{
    		    	if (this.surs[i].isSeen(v.getX(),v.getY(),v.getZ()))
    				return false;
    	}
    	return true;
    }
    
  
    
    /**
     * Action that draws the polygon
     * @param g graphics configuration
     */
       public void paintShape(Graphics g)
 	   {
 		    for(int i=0; i<this.surs.length;i++)
 		   {
 			 if(this.surs[i].isSeen(-4000,0,5000))
 			       this.surs[i].paintShape(g); 
 			}																				
 	    }
       
       
       /**
        * An action that draws the polygon
        * @param g graphics configuration
        */
          public void paintShapeDarker(Graphics g)
    	   {
    		    for(int i=0; i<this.surs.length;i++)
    		   {
    			 if(this.surs[i].isSeen(-4000,0,5000))
    			       this.surs[i].paintShapeDarker(g); 
    			}
    	    }
       
       /**
        *An operation that draws the polygon according to a color array containing the colors of the wigs,
        * Obtained according to a lighting model
        * @param g grafic
        * @param col color arry
        */
       public void paintShape(Graphics g,Color[] col)
 	   {
 		    for(int i=0; i<this.surs.length;i++)
 		   {
 			   if(this.surs[i].isSeen(-4000,-3500,50000))
 				   surs[i].paintShape(g,col[i]); 	     
 		   }
 	    }
       
       
       public void delay()
       {
    	   for(int i=0;i<999990000;i++);
       }
       
    
      
       /**
        * the body senter as average of X Y Z
        * @return body senter
        */
      public Vertex3D averagePoint()
       {
       double minX=this.vers[0].getX() ,maxX=this.vers[0].getX();
       double minY=this.vers[0].getY() ,maxY=this.vers[0].getY();
       double minZ=this.vers[0].getZ() ,maxZ=this.vers[0].getZ();
       int num=this.vers.length;
       for (int i=1;i<num; i++)
       {
             if (this.vers[i].getX()<minX) 
            	 minX=this.vers[i].getX();
             if (this.vers[i].getY()<minY) 
            	 minY=this.vers[i].getY();
             if (this.vers[i].getZ()<minZ) 
            	 minZ=this.vers[i].getZ();
             if (this.vers[i].getX()>maxX) 
            	 maxX=this.vers[i].getX();
             if (this.vers[i].getY()>maxY) 
            	 maxY=this.vers[i].getY();
             if (this.vers[i].getZ()>maxZ) 
            	 maxZ=this.vers[i].getZ();
       }
       return new Vertex3D((minX+maxX)/2,(minY+maxY)/2,(minZ+maxZ)/2);
       }
    
       
       
    }

