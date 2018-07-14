//-----------------------------------------------------------------------------
// Complex.java
// Mayowa Borisade
// mborisad
// pa6
// Represents complex numbers as a pair of doubles.
// -----------------------------------------------------------------------------

import java.lang.Math;
class Complex{
    //--------------------------------------------------------------------------
    // Private Data Fields 
    // --------------------------------------------------------------------------
    private double re;
    private double im;

    //--------------------------------------------------------------------------
    // Public Constant Fields
    //--------------------------------------------------------------------------
    public static final Complex ONE = Complex.valueOf(1,0);
    public static final Complex ZERO = Complex.valueOf(0,0);
    public static final Complex I = Complex.valueOf(0,1);

    //--------------------------------------------------------------------------
    // Constructors 
    //--------------------------------------------------------------------------
    Complex(double a, double b){
        this.re = a;
        this.im = b;
    }
    Complex(double a){
        this.re = a;
        this.im = 0;
    }

    Complex(String str){
      double[] part = new double[2];
      String s = str.trim();
      String NUM = "(\\d+\\.\\d*|\\.?\\d+)";
      String SGN = "[+-]?";
      String OP =  "\\s*[+-]\\s*";
      String I =   "i";
      String OR =  "|";
      String REAL = SGN+NUM;
      String IMAG = SGN+NUM+"?"+I;
      String COMP = REAL+OR+
                    IMAG+OR+
                    REAL+OP+NUM+"?"+I;
      
      if( !s.matches(COMP) ){
         throw new NumberFormatException(
                   "Cannot parse input string \""+s+"\" as Complex");
      }
      s = s.replaceAll("\\s","");     
      if( s.matches(REAL) ){
         part[0] = Double.parseDouble(s);
         part[1] = 0;
      }else if( s.matches(SGN+I) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I, "1.0" ) );
      }else if( s.matches(IMAG) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I , "" ) );
      }else if( s.matches(REAL+OP+I) ){
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+")"+OP+".+" , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+")"+I , "$1"+"1.0" ) );
      }else{   //  s.matches(REAL+OP+NUM+I) 
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+").+"  , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+NUM+")"+I , "$1" ) );
      }
      
      this.re = part[0];
      this.im = part[1];
    }
    //---------------------------------------------------------------------------
    // Public methods
    //---------------------------------------------------------------------------

    // Complex arithmetic -------------------------------------------------------
    
    // Return a new Complex equal to this Complex
    Complex copy(){
      return new Complex(this.re, this.im);
    }
    // Return a new Complex representing the sum this plus z.
    Complex add(Complex z){
        double re = this.re + z.Re();
        double im = this.im + z.Im();
        return (new Complex(re, im));
    } 

    // Return a new Complex representing the negative of this.
    Complex negate(){
        double re = this.re * -1;
        double im = this.im * -1;
        return (new Complex(re, im));
    }

    // Return a new Complex representing the difference this minus z.
    Complex sub(Complex z){
        double re = this.re - z.Re();
        double im = this.im - z.Im();
        return (new Complex(re, im));
    }

   
    // Return a new Complex representing the product this times z.
    Complex mult(Complex z){
        double re = ((this.re * z.Re()) - (this.im * z.Im()));
        double im = ((this.re * z.Im()) + (this.im * z.Re()));
        return (new Complex(re, im));
    }

  
    // Return a new Complex representing the reciprocal of this.
    // Throw an ArithmeticException with appropriate message if 
    // this.equals(Complex.ZERO).
    Complex recip(){
        if(this.equals(Complex.ZERO)){
            throw new ArithmeticException("Cannot reciprocate input string as Complex");
        }else{
            double re = (this.re) / (Math.pow(this.re,2) + Math.pow(this.im,2));
            double im = (this.im * -1) / (Math.pow(this.re,2) + Math.pow(this.im,2));
            return (new Complex(re, im));
        }
       
    }


    // Return a new Complex representing the quotient of this by z.
    // Throw an ArithmeticException with appropriate message if 
    // z.equals(Complex.ZERO).
    Complex div(Complex z){
        if(z.equals(Complex.ZERO)){
            throw new ArithmeticException("input cannot equal be divided");
        }else{
            double re = ((this.re * z.Re()) + (this.im * z.Im())) / (Math.pow(z.Re(),2) + Math.pow(z.Im(),2));
            double im = ((this.im * z.Re()) - (z.Im() * this.re)) / (Math.pow(z.Re(),2) + Math.pow(z.Im(),2));
            return (new Complex(re, im));
        }

    }


   // Return a new Complex representing the conjugate of this Complex.
    Complex conj(){
        double re = this.re;
        double im = this.im * -1;
        return (new Complex(re, im));
    }
   

    // Return the real part of this.
    double Re(){
        return re;
    }


    // Return the imaginary part of this.
    double Im(){
        return im;
    }


    // Return the modulus of this Complex, i.e. the distance between 
    // points (0, 0) and (re, im).
    double abs(){
        double sum = (Math.pow(this.re,2) + Math.pow(this.im,2));
        double sroot = Math.sqrt(sum);
        return sroot;
    }


    // Return the argument of this Complex, i.e. the angle this Complex
    // makes with positive real axis.
    double arg(){
        return Math.atan2(im, re);
    }

    // Other functions ---------------------------------------------------------
   

    // Return a String representation of this Complex.
    // The String returned will be readable by the constructor Complex(String s)
    public String toString(){
        String complex;
        if(im < 0){
            complex = re+ "" +im+ "i";
        }
        else if(im == 0){
            complex = re+ "";
        }
        else if(re == 0){
            complex = im+ "i";
        }else{
            complex = re+ "+" +im+ "i";
        }
        return complex;
    }

    // equals()
    // Return true iff this and obj have the same real and imaginary parts.
    public boolean equals(Object obj){
        Complex temp = (Complex)obj;
        if(this.re == temp.Re() && this.im == temp.Im()){
            return true;
        }
        return false;
    }


    // Return a new Complex with real part a and imaginary part b.
    static Complex valueOf(double a, double b){
        return(new Complex(a,b));
    }


    // Return a new Complex with real part a and imaginary part 0.
    static Complex valueOf(double a){
        return(new Complex(a,0));
    }


    // Return a new Complex constructed from s.
    static Complex valueOf(String s){
        return(new Complex(s));
    }

}
