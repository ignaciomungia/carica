package caricam.caricature.photo;

/**
 * Clase auxiliar para Warpeador.
 * Encapsula una matriz de 3 dimensiones.
 * @author Alejandro Luebs - aluebs@ieee.org
 */
public class Matrix {
    private short[][][] data;

    /**
     * Constructor de Matrix.
     * Crea una nueva matriz.
     * @param x cantidad de filas.
     * @param y cantidad de columnas.
     */
    public Matrix(short x,short y){
        data=new short[x][y][2];
    }

    /**
     * Inicializa la matriz con los valores de la fila y la columna de cada celda.
     */
    public void init(){
        for(short i=0;i<data.length;i++){
            for(short j=0;j<data[0].length;j++){
                data[i][j][0]=(short)i;
                data[i][j][1]=(short)j;
            }
        }
    }

    /**
     * @param i fila.
     * @param j columna.
     * @return d posicion en x.
     */
    public double getX(short i,short j){
        return (double)data[i][j][0];
    }

    /**
     * @param i fila.
     * @param j columna.
     * @return d posicion en y.
     */
    public double getY(short i,short j){
        return (double)data[i][j][1];
    }

    /**
     * @param i fila.
     * @param j columna.
     * @param d posicion en x.
     */
    public void setX(short i,short j,double d){
        data[i][j][0]=(short)d;
    }

    /**
     * @param i fila.
     * @param j columna.
     * @param d posicion en y.
     */
    public void setY(short i,short j,double d){
        data[i][j][1]=(short)d;
    }
    /**
     * @param i fila.
     * @param j columna.
     * @return d posicion en y.
     */
    public double getM(short i,short j){
        return (double)data[i][j][0];
    }

    /**
     * @param i fila.
     * @param j columna.
     * @param d posicion en x.
     */
    public void setM(short i,short j,double d){
        data[i][j][0]=(short)d;
    }


}