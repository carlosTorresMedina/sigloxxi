/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import InformeGeneral.InformeGeneralDto;
import ModeloVentas.VentaDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class PdfInformeGeneral {

    private  File directorio;
   private String ruta;  
  
    
    private Document documento;
    private FileOutputStream ficheroPdf;
    private int total;
    private String fechaIncial;
    private String fechaFinal;
    private String tipo;
    private ArrayList<InformeGeneralDto> nochePesos;
    private ArrayList<InformeGeneralDto> diaPesos;
    private ArrayList<InformeGeneralDto> nocheBolivares;
    private ArrayList<InformeGeneralDto> diaBolivares;
    private PdfPTable tabla;
    

    // subtotales
    private long ventas;
    private long comisiones;
    private long premios;
    private long utilidad;

    //totales final
    private long totalVentas;
    private long totalComision;
    private long totalPremios;
    private long totalUtilidad;

    
    
    public PdfInformeGeneral(){
     this.ruta="c:\\Reportes\\InformesGenerales\\";
     this.directorio=new File(this.ruta);
     this.directorio.mkdirs();
    }
    
    
    /**
     * genera el documento final de reportes
     *
     * @param fechaI
     * @param fechaF
     * @param nochePesos
     * @param diaPesos
     * @param nocheBolivares
     * @param diaBolivares
     * @return
     */
    public boolean generarPDFFactura(String fechaI, String fechaF, ArrayList<InformeGeneralDto> nochePesos, ArrayList<InformeGeneralDto> diaPesos, ArrayList<InformeGeneralDto> nocheBolivares, ArrayList<InformeGeneralDto> diaBolivares) {
       
        boolean dato = this.generarArchvo();
        this.fechaIncial = fechaI;
        this.fechaFinal = fechaF;
        this.nochePesos = nochePesos;
        this.diaPesos = diaPesos;
        this.nocheBolivares = nocheBolivares;
        this.diaBolivares = diaBolivares;

        try {
            documento.open();
            this.generarEncabezado();
            this.generarTabla1();
            documento.close();
        } catch (Exception ex) {
            dato = false;
            System.out.println("Error :" + ex.toString());
        }
        if (dato) {
            this.abrirDocumento();
        }
        return dato;
    }

    /**
     * genera la tabla con los valores de pesos y noche
     *
     * @throws DocumentException
     */
    private void generarTablaPesosNoche() throws DocumentException {
        this.tabla = new PdfPTable(5);
        this.adionarMedidasTabla();
        documento.add(new Paragraph("Moneda: Pesos.            Ruta: Noche   "));
        if (!nochePesos.isEmpty()) {
            for (InformeGeneralDto dto : nochePesos) {
                this.añadirRegistro(dto, tabla);
            }

        } else {
            InformeGeneralDto x = new InformeGeneralDto();
            x.setTipoUsuario("V");
            x.setPremios(0);
            x.setComision(0);
            x.setVentas(0);
            this.añadirRegistro(x, tabla);
            x.setTipoUsuario("P");
            this.añadirRegistro(x, tabla);
        }
        this.generarFilaTotales1();
    }

    /**
     * genera la tabla con los valores de pesos y dia
     *
     * @throws DocumentException
     */
    private void generarTablaPesosDia() throws DocumentException {
        this.tabla = new PdfPTable(5);
        this.adionarMedidasTabla();
        documento.add(new Paragraph("Moneda: Pesos.            Ruta: Dia  "));
        if (!diaPesos.isEmpty()) {
            for (InformeGeneralDto dto : diaPesos) {
                this.añadirRegistro(dto, tabla);
            }

        } else {
            InformeGeneralDto x = new InformeGeneralDto();
            x.setTipoUsuario("V");
            x.setPremios(0);
            x.setComision(0);
            x.setVentas(0);
            this.añadirRegistro(x, tabla);
            x.setTipoUsuario("P");
            this.añadirRegistro(x, tabla);
        }
        this.generarFilaTotales1();
    }

    /**
     * genera la tabla con los valores bolivares noche
     *
     * @throws DocumentException
     */
    private void generarTablaBolivaresNoche() throws DocumentException {
        this.tabla = new PdfPTable(5);
        this.adionarMedidasTabla();
        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("Moneda: Bolivares.            Ruta: Noche   "));
        if (!nocheBolivares.isEmpty()) {
            for (InformeGeneralDto dto : nocheBolivares) {
                this.añadirRegistro(dto, tabla);
            }

        } else {
            InformeGeneralDto x = new InformeGeneralDto();
            x.setTipoUsuario("V");
            x.setPremios(0);
            x.setComision(0);
            x.setVentas(0);
            this.añadirRegistro(x, tabla);
            x.setTipoUsuario("P");
            this.añadirRegistro(x, tabla);
        }
        this.generarFilaTotales1();

    }

    /**
     * genera la tabla con los valores de bolivares dia
     *
     * @throws DocumentException
     */
    private void generarTablaBolivaresDia() throws DocumentException {
        this.tabla = new PdfPTable(5);
        this.adionarMedidasTabla();
        documento.add(new Paragraph("Moneda: Bolivares.            Ruta: Dia   "));

        if (!diaBolivares.isEmpty()) {
            for (InformeGeneralDto dto : diaBolivares) {
                this.añadirRegistro(dto, tabla);
            }
        } else {
            InformeGeneralDto x = new InformeGeneralDto();
            x.setTipoUsuario("V");
            x.setPremios(0);
            x.setComision(0);
            x.setVentas(0);
            this.añadirRegistro(x, tabla);
            x.setTipoUsuario("P");
            this.añadirRegistro(x, tabla);
        }
        this.generarFilaTotales1();
    }

    /**
     * genera las 4 tablas para el informe general
     *
     * @throws DocumentException
     */
    private void generarTabla1() throws DocumentException {

        //genera las tablas de pesos en la noche y en el dia
        this.generarTablaPesosNoche();
        documento.add(tabla);
        this.generarTablaPesosDia();
        // genera los totales en pesos de dia y noche
        this.generarTotalesParaTipoMonedas();
         documento.add(tabla);
        //genera las tablas de bolivares en la noche y en el dia
        this.generarTablaBolivaresNoche();
        documento.add(tabla);
        this.generarTablaBolivaresDia();
        // genera los totales en pesos de dia y noche
        this.generarTotalesParaTipoMonedas();
         documento.add(tabla);

    }

    /**
     * adiciona al documento los totales de ventas, comision y premios para el
     * tipo de moneda
     *
     * @throws DocumentException
     */
    private void generarTotalesParaTipoMonedas() throws DocumentException {
        Font fuente = new Font();
        fuente.setStyle(Font.BOLD);
       NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        fuente.setSize(10);
        PdfPCell parrafo = new PdfPCell(new Paragraph("Gran total", fuente));
        tabla.addCell(parrafo);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalVentas), fuente));
         parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);

        PdfPCell parrafo3 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalComision) + "", fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);

        PdfPCell parrafo4 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalPremios), fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);
        
         PdfPCell parrafo5 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalUtilidad), fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        this.totalComision=0;
        this.totalPremios = 0;
        this.totalVentas = 0;
        this.totalUtilidad=0;
    }

    /**
     * genera las filas totales para cada tabla especifica.
     *
     * @throws DocumentException
     */
    private void generarFilaTotales1() throws DocumentException {
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        Font fuente = new Font();
        fuente.setSize(10);
        PdfPCell parrafo = new PdfPCell(new Paragraph("Totales", fuente));
        tabla.addCell(parrafo);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(ventas), fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);

        PdfPCell parrafo3 = new PdfPCell(new Paragraph(formatoNumero.format(comisiones) + "", fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);

        PdfPCell parrafo4 = new PdfPCell(new Paragraph(formatoNumero.format(premios), fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);
        
        PdfPCell parrafo5 = new PdfPCell(new Paragraph(formatoNumero.format(this.utilidad), fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);
        
             

        this.totalVentas += this.ventas;
        this.totalComision += this.comisiones;
        this.totalPremios += this.premios;
        this.totalUtilidad +=this.utilidad;

        this.ventas = 0;
        this.comisiones = 0;
        this.premios = 0;
        this.utilidad=0;

    }

    /**
     * se encarga de abrir el documento pdf generado.
     */
    private void abrirDocumento() {
        HiloDocumento hilo = new HiloDocumento(this.ruta);
        hilo.start();
    }

    /**
     * adiciona medidas a la tabla
     *
     * @throws DocumentException
     */
    private void adionarMedidasTabla() throws DocumentException {
        float[] medidaCeldas = {2f, 2.6f, 2.6f, 2.6f,2.6f};
        tabla.setWidths(medidaCeldas);
        tabla.setWidthPercentage(100f);
        tabla = formatoColumna(tabla);
    }

    /**
     * genera un archivo pdf a partir de una ruta
     *
     * @param ruta
     */
    private boolean generarArchvo() {
        boolean dato = false;
        documento = new Document();
        Date fecha=new Date();
       
        ruta = ruta +fecha.getHours()+fecha.getMinutes()+fecha.getSeconds()+ ".pdf";
        try {

            ficheroPdf = new FileOutputStream(ruta);

            PdfWriter.getInstance(
                    documento,
                    ficheroPdf
            ).setInitialLeading(20);
            dato = true;
        } catch (Exception ex) {

            System.err.println("Error :" + ex.getMessage());

        }
        return dato;
    }

    /**
     * genera el encabezado del reporte
     *
     * @throws DocumentException
     */
    private void generarEncabezado() throws DocumentException {
        Font fuente = new Font();
        fuente.setStyle(Font.BOLD);

        documento.add(new Paragraph("                                                  AGENCIA LOTERIA SIGLO XXI C.A.", fuente));
        documento.add(new Paragraph("LISTADO ANALISIS DE VENTAS Y PREMIOS "));
        String fecha = formatoFecha();
        documento.add(new Paragraph("Fecha de generacion de reporte: " + fecha));
        documento.add(new Paragraph("Analisis de ventas y premios en el rango de fecha : " + this.fechaIncial + "  al " + this.fechaFinal));
        documento.add(new Paragraph("\n"));
    }

    /**
     * aÃ±ade un determinado registro a una determinada tabla.
     *
     * @param dto registro
     * @param tabla tabla escogida para adicionar el registro
     */
    private void añadirRegistro(InformeGeneralDto dto, PdfPTable tabla) {
        Font fuente = new Font();
        fuente.setSize(10);
        PdfPCell parrafo = new PdfPCell(new Paragraph(dto.getTipoFormato() + "", fuente));
        tabla.addCell(parrafo);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(dto.getVentasFormato(), fuente));
         parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);

        PdfPCell parrafo3 = new PdfPCell(new Paragraph(dto.getComisionesFormato() + "", fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);

        PdfPCell parrafo4 = new PdfPCell(new Paragraph(dto.getPremiosFormato() + "", fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);
        
        PdfPCell parrafo5 = new PdfPCell(new Paragraph(dto.getUtilidadFormato() + "", fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        
        
        
        this.ventas += dto.getVentas();
        this.comisiones += dto.getComision();
        this.premios += dto.getPremios();
        this.utilidad +=dto.getUtilidad();

    }

    /**
     * da forrmato a las columnas
     *
     * @param tabla
     * @return
     */
    private PdfPTable formatoColumna(PdfPTable tabla) {
        PdfPCell columna[] = new PdfPCell[5];
        Font fuente = new Font();
        fuente.setSize(10);
        columna[0] = new PdfPCell(new Paragraph("Tipo", fuente));
        columna[0].setBackgroundColor(BaseColor.GRAY);

        columna[1] = new PdfPCell(new Paragraph("Ventas", fuente));
        columna[1].setBackgroundColor(BaseColor.GRAY);

        columna[2] = new PdfPCell(new Paragraph("Comision", fuente));
        columna[2].setBackgroundColor(BaseColor.GRAY);

        columna[3] = new PdfPCell(new Paragraph("Premio", fuente));
        columna[3].setBackgroundColor(BaseColor.GRAY);
        
        columna[4] = new PdfPCell(new Paragraph("Utilidad", fuente));
        columna[4].setBackgroundColor(BaseColor.GRAY);

        tabla.addCell(columna[0]);
        tabla.addCell(columna[1]);
        tabla.addCell(columna[2]);
        tabla.addCell(columna[3]);
        tabla.addCell(columna[4]);

        return tabla;

    }

    /**
     * establece un formtao para la fecha
     *
     * @return
     */
    private String formatoFecha() {

        DateFormat formato = DateFormat.getDateInstance();
        Date fecha = new Date();
        DateFormat formato2 = DateFormat.getDateInstance(DateFormat.FULL);
        return formato2.format(fecha);
    }
}
