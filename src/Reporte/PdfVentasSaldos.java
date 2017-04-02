/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

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
import java.util.Date;

/**
 *
 * @author carlos
 */
public class PdfVentasSaldos {

    private File directorio;
    private String ruta;
    
    private Document documento;
    private FileOutputStream ficheroPdf;
    private int ventas = 0;
    private int abonado = 0;
    private int comisiones = 0;
    private int abonos = 0;
    private int saldos = 0;
    private int saldoAnterior = 0;
    private int totalRegistrosP = 0;
    private int totalRegistrosN = 0;
    private int totalTicket=0;
    private String moneda;
    private String mes;
    private String rutaUsuario;
    private String tipo;
    private ArrayList<VentaDto> lista;
    private PdfPTable tabla;
   
    private String forma;

    public PdfVentasSaldos() {
             this.ruta="c:\\Reportes\\ReportesVentasSaldo\\";
     this.directorio=new File(this.ruta);
     this.directorio.mkdirs();
    }

    
    
    /**
     * genera el documento pdf de reportes
     *
     * @param datos
     * @param ruta
     */
    public boolean generarPDFFactura(ArrayList<VentaDto> lista,  String mes, String moneda, String ruta, String tipo, String forma) {
        
        boolean dato = this.generarArchvo();
        this.moneda = moneda;
        this.rutaUsuario = ruta;
        this.mes = mes;
        this.tipo = tipo;
        this.lista = lista;
        this.forma = forma;
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
        this.tabla = new PdfPTable(9);
        this.adionarMedidasTabla();
        for (VentaDto dto : lista) {
            if (this.forma.equalsIgnoreCase("POSITIVOS")) {
                if (dto.getSaldo() >= 0) {
                    this.totalRegistrosP++;
                    this.añadirRegistro(dto, tabla);
                }
            } else {
                if (dto.getSaldo() < 0) {
                    this.totalRegistrosN++;
                    this.añadirRegistro(dto, tabla);
                }
            }
        }

        this.generarFilaTotales();
    }

    private void generarFilaTotales() throws DocumentException {
        Font fuente = new Font();
        fuente.setSize(10);
        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Totales"));
        celdaFinal.setBackgroundColor(BaseColor.GRAY);
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        celdaFinal.setColspan(2);
        tabla.addCell(celdaFinal);

        PdfPCell parrafox = new PdfPCell(new Paragraph(formatoNumero.format(this.saldoAnterior), fuente));
        parrafox.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafox);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.ventas), fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);
        PdfPCell parrafo2 = new PdfPCell(new Paragraph(formatoNumero.format(this.comisiones), fuente));
        parrafo2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo2);
        PdfPCell parrafo3 = new PdfPCell(new Paragraph(formatoNumero.format(this.abonado), fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);
        PdfPCell parrafo4 = new PdfPCell(new Paragraph(formatoNumero.format(this.abonos), fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);
        PdfPCell parrafo5 = new PdfPCell(new Paragraph(formatoNumero.format(this.saldos), fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        PdfPCell parrafo6 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalTicket), fuente));
        parrafo6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo6);
        documento.add(tabla);
        if (this.forma.equalsIgnoreCase("POSITIVOS")) {
            documento.add(new Paragraph("Cantidad de registros : " + this.totalRegistrosP, fuente));
        } else {
            documento.add(new Paragraph("Cantidad de registros : " + this.totalRegistrosN, fuente));
        }
    }

    private void abrirDocumento() {
        HiloDocumento hilo = new HiloDocumento(this.ruta);
        hilo.start();
    }

    private void adionarMedidasTabla() throws DocumentException {
        float[] medidaCeldas = {1.2f, 3.5f, 2f, 2f, 2f, 2f, 2f, 2f,2f};
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
         Font fuente =new Font();
        fuente.setStyle(Font.BOLD);
        
        documento.add(new Paragraph("                                                  AGENCIA LOTERIA SIGLO XXI C.A.",fuente));
        documento.add(new Paragraph("REPORTE DE VENTAS DE SALDOS " + this.forma));
        String fecha = formatoFecha();
        documento.add(new Paragraph("Fecha de generacion de reporte: " + fecha));
        documento.add(new Paragraph("Tipo: " + tipo + "     Ruta: " + this.rutaUsuario));
        documento.add(new Paragraph("Moneda: " + moneda));
        documento.add(new Paragraph("\n"));
    }

    private void añadirRegistro(VentaDto dto, PdfPTable tabla) {
        Font fuente = new Font();
        fuente.setSize(10);
        PdfPCell parrafo = new PdfPCell(new Paragraph(dto.getPersona().getCodigo() + "", fuente));
        tabla.addCell(parrafo);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(dto.getPersona().getNombre() + " " + dto.getPersona().getApellido(), fuente));
        tabla.addCell(parrafo1);

        PdfPCell parrafox = new PdfPCell(new Paragraph(dto.getSaldoAnterioFormato() + "", fuente));
        parrafox.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafox);

        PdfPCell parrafo3 = new PdfPCell(new Paragraph(dto.getVentaFormato() + "", fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);

        PdfPCell parrafo4 = new PdfPCell(new Paragraph(dto.getComisionFormato() + "", fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);

        PdfPCell parrafo5 = new PdfPCell(new Paragraph(dto.getAbonoFormato() + "", fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        PdfPCell parrafo6 = new PdfPCell(new Paragraph(dto.getRecaudoFormato() + "", fuente));
        parrafo6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo6);

        PdfPCell parrafo7 = new PdfPCell(new Paragraph(dto.getSaldoFormato() + "", fuente));
        parrafo7.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo7);
        
        
        PdfPCell parrafo8 = new PdfPCell(new Paragraph(dto.getnTicketFormato() + "", fuente));
        parrafo8.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo8);

        ventas += dto.getVenta();
        comisiones += dto.getComision();
        abonos += dto.getRecaudo();
        saldos += dto.getSaldo();
        this.abonado += dto.getAbono();
        this.totalTicket+=dto.getnTicket();
    }

    /**
     * da forrmato a las columnas
     *
     * @param tabla
     * @return
     */
    private PdfPTable formatoColumna(PdfPTable tabla) {
        PdfPCell columna[] = new PdfPCell[9];
        Font fuente = new Font();
        fuente.setSize(10);
        columna[0] = new PdfPCell(new Paragraph("Codigo", fuente));
        columna[0].setBackgroundColor(BaseColor.GRAY);

        columna[1] = new PdfPCell(new Paragraph("Nombres", fuente));
        columna[1].setBackgroundColor(BaseColor.GRAY);

        columna[2] = new PdfPCell(new Paragraph("Saldo anterior", fuente));
        columna[2].setBackgroundColor(BaseColor.GRAY);

        columna[3] = new PdfPCell(new Paragraph("Ventas", fuente));
        columna[3].setBackgroundColor(BaseColor.GRAY);

        columna[4] = new PdfPCell(new Paragraph("Comision", fuente));
        columna[4].setBackgroundColor(BaseColor.GRAY);

        columna[5] = new PdfPCell(new Paragraph("Abonos", fuente));
        columna[5].setBackgroundColor(BaseColor.GRAY);

        columna[6] = new PdfPCell(new Paragraph("Neto", fuente));
        columna[6].setBackgroundColor(BaseColor.GRAY);

        columna[7] = new PdfPCell(new Paragraph("Saldo", fuente));
        columna[7].setBackgroundColor(BaseColor.GRAY);
        
         columna[8] = new PdfPCell(new Paragraph("Ticket", fuente));
        columna[8].setBackgroundColor(BaseColor.GRAY);

        tabla.addCell(columna[0]);
        tabla.addCell(columna[1]);
        tabla.addCell(columna[2]);
        tabla.addCell(columna[3]);
        tabla.addCell(columna[4]);
        tabla.addCell(columna[5]);
        tabla.addCell(columna[6]);
        tabla.addCell(columna[7]);
        tabla.addCell(columna[8]);

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
