import java.awt.*;


public class Polygon3D 
{
	protected Vertex3D[] vers;
	protected Color color; 

	
	public Polygon3D(Vertex3D[] vers, Color c)
	{
		this.vers=new Vertex3D[vers.length];
		for (int i=0;i<vers.length;i++)
			this.vers[i]=vers[i];
		this.color=c;
	}
	

	public Polygon3D(Polygon3D poly)
	{
		this.vers=new Vertex3D[poly.getVertex().length];
		for (int i=0;i<poly.getNum();i++)
			this.vers[i]=new Vertex3D(poly.vers[i]);
		this.color=poly.color;
	}
	

	public int getNum()
	{
		return this.vers.length;
	}
	
	
	public Vertex3D[] getVertex()
	{
		return this.vers;
	}
	

	public Color getColor()
	{
		return this.color;
	}
	
	
	public void setColor(Color c)
	{
		this.color=c;
	}
	
	
	public void setVertex(Vertex3D[] vers)
	{
		this.vers=new Vertex3D[vers.length];
		for (int i=0;i<vers.length;i++)
			this.vers[i]=new Vertex3D(vers[i]);
	}
	

	public Polygon parallelPoly()
	{
		int[] xpoints=new int[this.getNum()];
    	int[] ypoints=new int[this.getNum()] ;
		for (int i=0;i<this.getNum();i++)
		{
			xpoints[i]=(int)this.vers[i].parallelProj().getX();
			ypoints[i]=(int)this.vers[i].parallelProj().getY();
		}
		return new Polygon(xpoints,ypoints,this.getNum());	
		
	}
	
	public Polygon perspectivePoly()
	{
		int[] xpoints=new int[this.getNum()];
    	int[] ypoints=new int[this.getNum()] ;
		for (int i=0;i<this.getNum();i++)
		{
			xpoints[i]=(int)this.vers[i].perspectiveProj().getX();
			ypoints[i]=(int)this.vers[i].perspectiveProj().getY();
		}
		return new Polygon(xpoints,ypoints,this.getNum());	
		
	}
	
	
	public double[] getPlane()// A B C D
	{
		Vertex3D v1=this.vers[0];
		Vertex3D v2=this.vers[1];
		Vertex3D v3=this.vers[2];
		double[] vers=new double[4];
		vers[0]=v1.getY()*(v2.getZ()-v3.getZ())+v2.getY()*(v3.getZ()-v1.getZ())+v3.getY()*(v1.getZ()-v2.getZ());
		vers[1]=v1.getZ()*(v2.getX()-v3.getX())+v2.getZ()*(v3.getX()-v1.getX())+v3.getZ()*(v1.getX()-v2.getX());
		vers[2]=v1.getX()*(v2.getY()-v3.getY())+v2.getX()*(v3.getY()-v1.getY())+v3.getX()*(v1.getY()-v2.getY());
		vers[3]=-v1.getX()*(v2.getY()*v3.getZ()-v3.getY()*v2.getZ())
			    -v2.getX()*(v3.getY()*v1.getZ()-v1.getY()*v3.getZ())
			    -v3.getX()*(v1.getY()*v2.getZ()-v2.getY()*v1.getZ());
		//System.out.println("plane "+vers[0]+" "+vers[1]+" "+vers[2]+" "+vers[3]);
		return vers;
	}
	
	
    public boolean isSeen(double x0,double y0,double z0)
    {
    	double[] vers=this.getPlane(); // ����� ������ ������� ��� ������� ������
    	 // ����� ������ ������� ��� ������� ������
        return vers[0]*x0+vers[1]*y0+vers[2]*z0+vers[3]>0;
     }
    
 
    public void paintShape(Graphics g)
    {
    	Polygon poly=this.parallelPoly();
    	g.setColor(this.color);
     	g.fillPolygon(poly);
    	g.setColor(Color.black);
    	g.drawPolygon(poly);
    }
    
  
    public void paintShapeDarker(Graphics g)
    {
    	Polygon poly=this.parallelPoly();
    	g.setColor(this.color.darker());
     	g.fillPolygon(poly);
    	g.setColor(Color.black);
    	g.drawPolygon(poly);
    }
   
 
    public void paintShape(Graphics g,Color col)
    {
    	Polygon poly=this.parallelPoly();
    	g.setColor(col);
    	g.fillPolygon(poly);
    }
    
   
   
 
    public double dVertexPlane(Vertex3D v1)
    {
    	double[] vers=getPlane(); // ����� ������ ������� ��� ������� ������
    	return (Math.abs(vers[0]*v1.getX()+vers[1]*v1.getY()+vers[2]*v1.getZ()+vers[3]))/
    	       (Math.sqrt(vers[0]*vers[0]+vers[1]*vers[1]+vers[2]*vers[2])); 
    }
    
         
 
    public Vertex3D minVertex()
    {
    	Vertex3D minVer=this.vers[0];
    	for(int i=0;i<this.getNum();i++)
    		if (vers[i].getZ()<minVer.getZ())
    			    minVer=this.vers[i];
    	for(int i=0;i<this.getNum();i++)
    		if (vers[i].getZ()==minVer.getZ())
    			   if (vers[i].getX()<minVer.getX())
    			       minVer=this.vers[i];
    	for(int i=0;i<this.getNum();i++)
    		if ((vers[i].getZ()==minVer.getZ()) && (vers[i].getX()==minVer.getX()))
    			   if (vers[i].getY()<minVer.getY())
    			       minVer=this.vers[i];
      	return new Vertex3D(minVer);
    } 
 
    private double minX()
    {
 	   double min=this.vers[0].getX();
 	   for (int i=1;i<this.vers.length;i++)
 		   if (this.vers[i].getX()<min)
 			   min=this.vers[i].getX();
 	   return min;
 	}

    public double maxX()
    {
 	   double max=this.vers[0].getX();
 	   for (int i=1;i<this.vers.length;i++)
 		   if (this.vers[i].getX()>max)
 			  max=this.vers[i].getX();
 	   return max;
 	}
    
 
    public double minY()
    {
 	   double min=this.vers[0].getY();
 	   for (int i=1;i<this.vers.length;i++)
 		   if (this.vers[i].getY()<min)
 			   min=this.vers[i].getY();
 	   return min;
 	}
    

    public double maxY()
    {
 	   double max=this.vers[0].getY();
 	   for (int i=1;i<this.vers.length;i++)
 		   if (this.vers[i].getY()>max)
 			  max=this.vers[i].getY();
 	   return max;
 	}
    
      
    

    public double minZ()
    {
    	
       Vertex3D min=this.vers[0];
 	   for (int i=1;i<this.vers.length;i++)
 		   if (this.vers[i].getZ()<min.getZ())
 			   min=this.vers[i];
 	   return min.getZ();
 	}
    

    public double maxZ()
    {
    	
       Vertex3D max=this.vers[0];
 	   for (int i=1;i<this.vers.length;i++)
 		   if (this.vers[i].getZ()>max.getZ())
 			   max=this.vers[i];
 	   return max.getZ();
 	}

    public boolean omekZ(Polygon3D sTag)
    {   
     //System.out.println("bdika 0 " );
     return (((sTag.minZ()>=this.minZ()) && (sTag.minZ()<=this.maxZ()))||
    			 ((sTag.maxZ()>=this.minZ()) && (sTag.maxZ()<=this.maxZ()))||
    			 ((this.minZ()>=sTag.minZ()) && (this.minZ()<=sTag.maxZ()))||
    	    	 ((this.maxZ()>=sTag.minZ()) && (this.maxZ()<=sTag.maxZ())));
    }
    

    public boolean rectCutRect(Polygon3D sTag)
    {   
    	System.out.println("bdika 1 ");
    	Polygon p1=this.parallelPoly();
    	Polygon p2=sTag.parallelPoly();
    	Rectangle r1=p1.getBounds();
    	Rectangle r2=p2.getBounds();
     	int r1x=r1.x;
    	int r1y=r1.y;
    	int r1xx=r1.x+r1.width;
    	int r1yy=r1.y+r1.height;
    	int r2x=r2.x;
    	int r2y=r2.y;
    	int r2xx=r2.x+r2.width;
    	int r2yy=r2.y+r2.height;
    	if ( (((r2x>=r1x) && (r2x<=r1xx)) && ((r2y>=r1y) && (r2y<=r1yy))) ||
    		 (((r2xx>=r1x) && (r2xx<=r1xx)) && ((r2y>=r1y) && (r2y<=r1yy))) ||
    		 (((r2x>=r1x) && (r2x<=r1xx)) && ((r2yy>=r1y) && (r2yy<=r1yy))) ||
    		 (((r2xx>=r1x) && (r2xx<=r1xx)) && ((r2yy>=r1y) && (r2yy<=r1yy))))
    		 return true;
    	if ( (((r1x>=r2x) && (r1x<=r2xx)) && ((r1y>=r2y) && (r1y<=r2yy))) ||
       		 (((r1xx>=r2x) && (r1xx<=r2xx)) && ((r1y>=r2y) && (r1y<=r2yy))) ||
       		 (((r1x>=r2x) && (r1x<=r2xx)) && ((r1yy>=r2y) && (r1yy<=r2yy))) ||
       		 (((r1xx>=r2x) && (r1xx<=r2xx)) && ((r1yy>=r2y) && (r1yy<=r2yy))))
    		 return true;
   	   	return false; 
    //	return r1.intersects(r2);
    }
    

    public boolean behind(Polygon3D sTag) 
    {
    	System.out.println("bdika 2 ");
        double[] arr=sTag.getPlane();
        double n=-4000*arr[0]+-5000*arr[1]+500000*arr[2]+arr[3];//����� ���
       
    	for(int i=0;i<this.vers.length;i++)
    	{
    		if (n*(this.vers[i].getX()*arr[0]+this.vers[i].getY()*arr[1]+this.vers[i].getZ()*arr[2]+arr[3])>0)
            return  false;
    	}
       	return true;
    }
    

    public boolean before(Polygon3D sTag) 
    {
    	System.out.println("bdika 3 ");
    	double[] arr=this.getPlane();
        double n=-4000*arr[0]+-5000*arr[1]+500000*arr[2]+arr[3];//����� ���
    	for(int i=0;i<sTag.vers.length;i++)
    	{
    		if (n*(sTag.vers[i].getX()*arr[0]+sTag.vers[i].getY()*arr[1]+sTag.vers[i].getZ()*arr[2]+arr[3])<0)
    			return  false;
    	}
   	
    	return true;
    }
    
        

    public boolean polyCutPoly(Polygon3D sTag)
    {
    	System.out.println("bdika 4 ");
    	Polygon p1=this.parallelPoly(); 
    	Polygon p2=sTag.parallelPoly();
    	int minx=p1.xpoints[0];
    	int maxx=p1.xpoints[0];
    	int miny=p1.xpoints[0];
    	int maxy=p1.xpoints[0];
    	for(int i=0;i<p1.npoints;i++)
    	{
    		if (p1.xpoints[i]<minx)
    			minx=p1.xpoints[i];
    		if (p1.xpoints[i]>maxx)
    			maxx=p1.xpoints[i];
    		if (p1.ypoints[i]<miny)
    			miny=p1.ypoints[i];
    		if (p1.ypoints[i]>maxy)
    			maxy=p1.ypoints[i];
    	}
    	int x=(minx+maxx)/2;
    	int y=(miny+maxy)/2;
    	for(int i=0;i<p1.npoints;i++)
    	{
    		if (p1.xpoints[i]<x)
    			p1.xpoints[i]-=1;
    		if (p1.xpoints[i]>x)
    			p1.xpoints[i]+=1;
    		if (p1.ypoints[i]<y)
    			p1.ypoints[i]-=1;
    		if (p1.ypoints[i]>y)
    			p1.ypoints[i]+=1;
    	}
    	
    	Point point;
        	
       for (int i=0;i<p1.npoints;i++)
    	{
    		 point=new Point(p1.xpoints[i],p1.ypoints[i]);
    		 if (p2.contains(point));
    			 return true;
    	}
    	for(int j=0;j<p2.npoints;j++)
    	{
    		point=new Point(p2.xpoints[j],p2.ypoints[j]);
   		    if (p1.contains(point));
   		         return true;
    	}
    	return false;
    }
    

    public Vertex3D average()
    {
        int n=this.vers.length;
    	int sumX=0,sumY=0,sumZ=0;
  	   for (int i=1;i<n;i++)
  	   {
  		   sumX+=this.vers[i].getX();
  		   sumY+=this.vers[i].getY();
  		   sumZ+=this.vers[i].getZ();
  	   }
  	   return new Vertex3D(sumX/n,sumY/n,sumZ/n);
    	
    }
    

    public String toString()
    {
    	for(int i=0;i<this.getNum();i++)
    	   System.out.print(this.vers[i]+"  ");
    	System.out.println();
    	return " ";
    }
    	
    
}
