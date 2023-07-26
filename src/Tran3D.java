/** 3D transform class
 * 4x4 each bone contains a size matrix
 */
public class Tran3D  
{
	private double[][] mat;

/**
* Constructive action
* Creates the unit matrix
*/
    public Tran3D()
    {
    	this.mat=new double[4][4];
    	this.yechida();
    }
    
/**
     * Changes the matrix to the unit matrix
     */
    public void yechida()
    {
    	for(int i=0;i<4;i++)
    		for(int j=0;j<4;j++)
    			this.mat[i][j]=0;
    	this.mat[0][0]=1;
    	this.mat[1][1]=1;
    	this.mat[2][2]=1;
    	this.mat[3][3]=1;
    }
    
    /**
     * Changes the matrix according to the matrix received as a parameter
     * @param mat - some matrix
     */
    public void setMat(double[][] mat)
    {
    	for(int i=0;i<4;i++)
    		for(int j=0;j<4;j++)
    			this.mat[i][j]=mat[i][j];
    }
    
 /**
     * The operation changes the matrix to a shifting matrix
     * @param tx - x axis shift factor
     * @param ty - y Shift factor on axis
     * @param tz - z axis shift factor
     */
    public void hazaza(double tx,double ty,double tz)
//{The procedure generates a shift matrix}
    {
    	this.yechida();
    	this.mat[3][0]=tx; 
        this.mat[3][1]=ty;
        this.mat[3][2]=tz;
        
        this.mat[3][3]=1;
    }
    
/**
     * The operation changes the matrix to a Silom matrix
     * @param sx - x scale factor on axis
     * @param sy - y scale factor on axis
     * @param sz - y scale factor on axis
     */
    public void silum(double sx,double sy,double sz)
    {
    	this.yechida();
    	this.mat[0][0]=sx; 
        this.mat[1][1]=sy; 
        this.mat[2][2]=sz;
        this.mat[3][3]=1;
    }
    
/** * matrix changes the matrix to a rotation matrix around the x axis * @param z - the rotation angle */
    public void sivuv_x(double z)
    {
    	this.yechida();
     	this.mat[1][1]=Math.cos(z);   
    	this.mat[1][2]=Math.sin(z);
        this.mat[2][1]=-Math.sin(z);  
        this.mat[2][2]=Math.cos(z);         
    }
    
    /*** The y matrix changes the matrix to a rotation matrix around an axis
     * @param z - the rotation angle
     */
    public void sivuv_y(double z)
    {
    	this.yechida();
    	this.mat[0][0]=Math.cos(z);   
    	this.mat[0][2]=-Math.sin(z);
        this.mat[2][0]=Math.sin(z);  
        this.mat[2][2]=Math.cos(z);         
    }
 /**
     * The z matrix changes the matrix to a rotation matrix around an axis
     * @param z - the rotation angle
     */
    public void sivuv_z(double z)
    {
    	this.yechida();
    	this.mat[0][0]=Math.cos(z);   
    	this.mat[0][1]=Math.sin(z);
        this.mat[1][0]=-Math.sin(z);  
        this.mat[1][1]=Math.cos(z);         
    }
    
/**
     * The function multiplies the homogeneous point passed as a parameter in the matrix and returns a homogeneous point
     * @param vec - homogeneous point
     * @return - a homogeneous point which is the product
     */
    public double[] mullVecMat(double[] vec)
    {
        double[] vec1=new double[4]; 	
       	for(int i=0;i<4;i++)
    	{	
    	       vec1[i]=0;
    	       for(int j=0;j<4;j++)
    	           vec1[i]=vec1[i]+vec[j]*this.mat[j][i];
    	}
       	return vec1;
    }
/**
     * The operation multiplies a matrix by a matrix and returns the product matrix
     * @param mat1 - first matrix in the product
     * @param mat2 - second matrix in the product
     * @return the product matrix
     */
    public Tran3D mullMatMat(Tran3D t)
    {
    	Tran3D t1=new Tran3D();
    	double[][] mat=new double[4][4]; 	
       	for(int i=0;i<4;i++)
    	    for(int j=0;j<4;j++)
    	    {
    	    	mat[i][j]=0;
    	    	for (int k=0;k<4;k++)
    	    		mat[i][j]=mat[i][j]+this.mat[i][k]*t.mat[k][j];
   	    	}
       	t1.setMat(mat);
       	return t1;
    }

    /**
   * The operation changes the matrix to a Silom matrix relative to the Saturday point
     * @param sx - x scale factor on axis
     * @param sy - y scale factor on axis
     * @param sz - z scale factor on axis
     * @param xf - the reset point x value
     * @param yf - the reset point y value
     * @param zf - the reset point z value
     */
    public void silumShevet(double sx,double sy,double sz,double xf,double yf,double zf)
    {
    	Tran3D mat1=new Tran3D();
    	Tran3D mat2=new Tran3D();
    	Tran3D mat3=new Tran3D();
    	Tran3D mat4=new Tran3D();
    	Tran3D mat5=new Tran3D();
    	mat1.hazaza(-xf,-yf,-zf);
    	mat2.silum(sx,sy,sz);
    	mat3.hazaza(xf,yf,zf);
    	mat4=mat1.mullMatMat(mat2);
    	mat5=mat4.mullMatMat(mat3);
    	this.setMat(mat5.mat);
    }
    
/**
     * The x operation changes the matrix to a rotation matrix around an axis point parallel to the e-axis
     * @param zavit - the rotation angle
     * @param xr - the x-axis point value
     * @param yr - the y-axis point value
     * @param zr - the z axis point value
     */
    public void sivuvZirX(double zavit,double xr,double yr,double zr)
    {
    	Tran3D mat1=new Tran3D();
    	Tran3D mat2=new Tran3D();
    	Tran3D mat3=new Tran3D();
    	Tran3D mat4=new Tran3D();
    	Tran3D mat5=new Tran3D();
    	mat1.hazaza(-xr,-yr,-zr);
    	mat2.sivuv_x(zavit);
    	mat3.hazaza(xr,yr,zr);
    	mat4=mat1.mullMatMat(mat2);
    	mat5=mat4.mullMatMat(mat3);
    	this.setMat(mat5.mat);
    }
    /**
     * The y operation changes the matrix to a rotation matrix around an axis point parallel to the e-axis
     * @param zavit - the rotation angle
     * @param xr - the x-axis point value
     * @param yr - the y-axis point value
     * @param zr - the z axis point value
     */
    public void sivuvZirY(double zavit,double xr,double yr,double zr)
    {
    	Tran3D mat1=new Tran3D();
    	Tran3D mat2=new Tran3D();
    	Tran3D mat3=new Tran3D();
    	Tran3D mat4=new Tran3D();
    	Tran3D mat5=new Tran3D();
    	mat1.hazaza(-xr,-yr,-zr);
    	mat2.sivuv_y(zavit);
    	mat3.hazaza(xr,yr,zr);
    	mat4=mat1.mullMatMat(mat2);
    	mat5=mat4.mullMatMat(mat3);
    	this.setMat(mat5.mat);
    }
    
   /**
     * The z operation changes the matrix to a rotation matrix around an axis point parallel to the e-axis
     * @param zavit - the rotation angle
     * @param xr - the x-axis point value
     * @param yr - the y-axis point value
     * @param zr - the z axis point value
     */
    public void sivuvZirZ(double zavit,double xr,double yr,double zr)
    {
    	Tran3D mat1=new Tran3D();
    	Tran3D mat2=new Tran3D();
    	Tran3D mat3=new Tran3D();
    	Tran3D mat4=new Tran3D();
    	Tran3D mat5=new Tran3D();
    	mat1.hazaza(-xr,-yr,-zr);
    	mat2.sivuv_z(zavit);
    	mat3.hazaza(xr,yr,zr);
    	mat4=mat1.mullMatMat(mat2);
    	mat5=mat4.mullMatMat(mat3);
    	this.setMat(mat5.mat);
    }
    
 /**
     * An operation that returns the transformation matrix
     * @return the transformation matrix
     */
    public double[][] getMat()
    {
    	return this.mat;
    }
    
    public String toString()
    {
    	String s="";
    	for(int i=0;i<4;i++)
    		for(int j=0;j<4;j++)
    			s=s+ " "+this.mat[i][j];
    	return s;
    }
 }
