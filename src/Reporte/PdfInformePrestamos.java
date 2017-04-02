/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import ModeloVentas.PersonaDto;
import ModeloVentas.VentaDto;

import ModuloPrestamos.PrestamoDto;
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
public class PdfInformePrestamos {

      private  File directorio;
   private String ruta; 
   private String fechaI;
   private String fechaF;
    
    private Document documento;
    private FileOutputStream ficheroPdf;
    private int deuda;
    private String moneda;
    private PersonaDto persona;
    private ArrayList<PrestamoDto> prestamos;
    private ArrayList<PrestamoDto> pagos;
    private PdfPTable tabla;

    private int totalP;
    private int totalPa;

    public PdfInformePrestamos() {
    this.ruta="c:\\Reportes\\ReportesPrestamos\\";
     this.directorio=new File(this.ruta);
     this.directorio.mkdirs();
    }

    
    
    /**
     * genera el documento pdf de reportes
     *
     * @param datos
     * @param ruta
     */
    public boolean generarPDFFactura(ArrayList<PrestamoDto> prestamos, ArrayList<PrestamoDto> pagos, PersonaDto persona, String moneda,String fechaI,String fechaF) {
     
        boolean dato = this.generarArchvo();
        this.moneda = moneda;
        this.prestamos = prestamos;
        this.pagos = pagos;
        this.persona = persona;
        this.fechaI=fechaI;
        this.fechaF=fechaF;
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
        this.generarTablaPrestamos();
        this.generarTablaPagos();
         NumberFormat formatoNumero = NumberFormat.getNumberInstance();
         this.deuda=this.totalP-this.totalPa;
              documento.add(new Paragraph("Deuda actual: "+formatoNumero.format(deuda)));
        
    }

    private void generarTablaPrestamos() throws DocumentException {
       
        documento.add(new Paragraph("Prestamos realizados "));
        this.tabla = new PdfPTable(2);
        this.adionarMedidasTabla();
        for (PrestamoDto dto : this.prestamos) {
            this.añadirRegistroPrestamo(dto, tabla);
        }
        this.generarFilaTotalesPrestamo();
    }

    private void generarTablaPagos() throws DocumentException {

        documento.add(new Paragraph("\n "));
        documento.add(new Paragraph("Pagos realizados"));
        this.tabla = new PdfPTable(2);
        this.adionarMedidasTabla();
        for (PrestamoDto dto : this.pagos) {
            this.añadirRegistroPago(dto, tabla);
        }
        this.generarFilaTotalesPago();
    }

    private void generarFilaTotalesPrestamo() throws DocumentException {
        Font fuente = new Font();
        fuente.setSize(10);

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Total"));
        celdaFinal.setBackgroundColor(BaseColor.GRAY);
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        celdaFinal.setColspan(1);
        tabla.addCell(celdaFinal);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalP), fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);

        documento.add(tabla);

    }

    private void generarFilaTotalesPago() throws DocumentException {
        Font fuente = new Font();
        fuente.setSize(10);

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Total"));
        celdaFinal.setBackgroundColor(BaseColor.GRAY);
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        celdaFinal.setColspan(1);
        tabla.addCell(celdaFinal);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalPa), fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);

        documento.add(tabla);

    }

    private void abrirDocumento() {
        HiloDocumento hilo = new HiloDocumento(this.ruta);
        hilo.start();
    }

    private void adionarMedidasTabla() throws DocumentException {
        float[] medidaCeldas = {1.0f, 2f};
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
        documento.add(new Paragraph("REPORTE DE PRESTAMOS "));
        String fecha = formatoFecha();
        documento.add(new Paragraph("Fecha de generacion de reporte: " + fecha));
        documento.add(new Paragraph("Codigo: " + this.persona.getCodigo()));
        documento.add(new Paragraph("Documento de identificacion: " + this.persona.getNuip()));
        documento.add(new Paragraph("Nombre completo: " + this.persona.getNombre() + " " + this.persona.getApellido()));
        documento.add(new Paragraph("Moneda: " + moneda));
         documento.add(new Paragraph("Prestamos generados en el rango de fecha: " + this.fechaI+" - "+this.fechaF));
        documento.add(new Paragraph("\n"));
    }

    private void añadirRegistroPrestamo(PrestamoDto dto, PdfPTable tabla) {
        Font fuente = new Font();
        fuente.setSize(10);
        PdfPCell parrafo = new PdfPCell(new Paragraph(dto.getFecha(), fuente));
        tabla.addCell(parrafo);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(dto.getValorFormato(), fuente));
         parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);
        this.totalP += dto.getValor();

    }

    public void añadirRegistroPago(PrestamoDto dto, PdfPTable tabla) {
        Font fuente = new Font();
        fuente.setSize(10);
        PdfPCell parrafo = new PdfPCell(new Paragraph(dto.getFecha(), fuente));
        tabla.addCell(parrafo);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(dto.getValorFormato(), fuente));
         parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);
        this.totalPa += dto.getValor();
    }

    /**
     * da forrmato a las columnas
     *
     * @param tabla
     * @return
     */
    private PdfPTable formatoColumna(PdfPTable tabla) {
        PdfPCell columna[] = new PdfPCell[2];
        Font fuente = new Font();
        fuente.setSize(10);
        columna[0] = new PdfPCell(new Paragraph("Fecha", fuente));
        columna[0].setBackgroundColor(BaseColor.GRAY);

        columna[1] = new PdfPCell(new Paragraph("Valor", fuente));
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
