/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import ModeloPremio.PremioDto;
import ModeloPremio.TicketDto;
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
import java.util.Date;

/**
 *
 * @author carlos
 */
public class PdfChance {
    
   private  File directorio;
   private String ruta;
    
      private Document documento;
    private FileOutputStream ficheroPdf;
    private int totalPremio = 0;
   private int totalRegistros=0;
    private String moneda;

    public PdfChance(){
        this.ruta="c:\\Reportes\\reportesChance\\";
     this.directorio=new File(this.ruta);
     this.directorio.mkdirs();
    }
    
    //fecha de consulta
    private String fechaConsulta;

    private ArrayList<TicketDto> lista;
    private PdfPTable tabla;
    
    
    public boolean generarPDFFactura(ArrayList<TicketDto> premios, String fecha, String moneda) {
        
        boolean dato = this.generarArchvo();
        this.moneda = moneda;
        this.lista = premios;
        this.fechaConsulta = fecha;
        try {
            documento.open();
            this.generarEncabezado();
            this.generarTabla();
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

    private void generarTabla() throws DocumentException {
        this.tabla = new PdfPTable(2);
        this.adionarMedidasTabla();
        for (TicketDto dto : lista) {
            this.totalRegistros++;
            this.totalPremio +=dto.getValor();
            this.añadirRegistro(dto, tabla);
        }
        this.generarFilaTotales();
    }

    private void generarFilaTotales() throws DocumentException {
       
        
        
        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Totales"));
        celdaFinal.setBackgroundColor(BaseColor.GRAY);
        celdaFinal.setColspan(1);
        
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        tabla.addCell(celdaFinal);
        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalPremio)));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);
        
        documento.add(tabla);
        
        documento.add(new Paragraph("Cantidad de registros: " +this.totalRegistros));
    }

    private void abrirDocumento() {
        HiloDocumento hilo = new HiloDocumento(this.ruta);
        hilo.start();
    }

    private void adionarMedidasTabla() throws DocumentException {
        float[] medidaCeldas = {2.5f, 1.8f};
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
        documento.add(new Paragraph("REPORTE DE VENTAS CHANCE"));
        String fecha = formatoFecha();
        documento.add(new Paragraph("Fecha de generacion de reporte: " + fecha));
        documento.add(new Paragraph("Ventas realizadas en la fecha de " + this.fechaConsulta));
        documento.add(new Paragraph("Moneda: " + moneda));
        documento.add(new Paragraph("\n"));
    }

    private void añadirRegistro(TicketDto dto, PdfPTable tabla) {
              
        Font fuente= new Font();
        fuente.setSize(10);    
        PdfPCell parrafo = new PdfPCell(new Paragraph(dto.getCodigo() + "",fuente));
        tabla.addCell(parrafo);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(dto.getValor()+"",fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);

    }

    /**
     * da forrmato a las columnas
     *
     * @param tabla
     * @return
     */
    private PdfPTable formatoColumna(PdfPTable tabla) {
        PdfPCell columna[] = new PdfPCell[2];
        Font fuente= new Font();
        fuente.setSize(10);
        columna[0] = new PdfPCell(new Paragraph("Vendedor",fuente));
        columna[0].setBackgroundColor(BaseColor.GRAY);

        columna[1] = new PdfPCell(new Paragraph("Venta",fuente));
        columna[1].setBackgroundColor(BaseColor.GRAY);

      
        
        tabla.addCell(columna[0]);
        tabla.addCell(columna[1]);
     

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
