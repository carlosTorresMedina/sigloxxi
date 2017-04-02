/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import ModeloVentas.PersonaDto;
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
public class PdfVentaPersona {
    
     private File directorio;
    private String ruta;
    
     private Document documento;
    private FileOutputStream ficheroPdf;
    private int ventas = 0;
    private int abonado=0;
    private int comisiones = 0;
    private int abonos = 0;
    private int saldos = 0;
    private int totalRegistros=0;
    private int totalTicket=0;
    private String moneda;
    private String fechaIncial;
    private String fechaFinal;
    private String rutaUsuario;
    
    private ArrayList<VentaDto> lista;
    private PdfPTable tabla;
    
    private PersonaDto persona;

    public PdfVentaPersona() {
         this.ruta="c:\\Reportes\\ReportesVentasPersona\\";
     this.directorio=new File(this.ruta);
     this.directorio.mkdirs();
    }

    
    
    /**
     * genera el documento pdf de reportes
     *
     * @param listas
     * @param rutaArchivo
     * @param datos
     * @param ruta
     */
    public boolean generarPDFFactura(ArrayList<VentaDto> listas,  String fechaInicial, String fechaFinal,String moneda,String ruta,PersonaDto dto) {
       
        boolean dato = this.generarArchvo();
        this.moneda = moneda;
       this.rutaUsuario=ruta;
       this.fechaIncial=fechaInicial;
       this.fechaFinal=fechaFinal;
       this.persona=dto;
        this.lista = listas;
       
        try {
            documento.open();
            this.generarEncabezado();
            this.generarTabla();
            documento.close();
        } catch (Exception ex) {
            dato = false;
            System.out.println("Error :" + ex.toString());
        }
        if(dato){
            this.abrirDocumento();
        }
        return dato;
    }

    private void generarTabla() throws DocumentException {
        this.tabla = new PdfPTable(7);
        this.adionarMedidasTabla();
        for (VentaDto dto : lista) {
            this.totalRegistros++;
            this.añadirRegistro(dto, tabla);
        }
        this.generarFilaTotales();
    }

    private void generarFilaTotales() throws DocumentException {
         Font fuente= new Font();
        fuente.setSize(10);
        
        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Totales"));
        celdaFinal.setBackgroundColor(BaseColor.GRAY);
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        celdaFinal.setColspan(1);
        tabla.addCell(celdaFinal);
          
    
        
        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.ventas),fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);
        
        PdfPCell parrafo2 = new PdfPCell(new Paragraph(formatoNumero.format(this.comisiones),fuente));
        parrafo2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo2 );
        
         PdfPCell parrafo3 = new PdfPCell(new Paragraph(formatoNumero.format(this.abonado),fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3 );
        
        PdfPCell parrafo4 = new PdfPCell(new Paragraph(formatoNumero.format(this.abonos),fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);
        
        PdfPCell parrafo5 = new PdfPCell(new Paragraph(formatoNumero.format(this.saldos),fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        PdfPCell parrafo6 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalTicket),fuente));
        parrafo6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo6);
        
        documento.add(tabla);
        documento.add(new Paragraph("Cantidad de registros : "+this.totalRegistros,fuente));
    }

    
    private void abrirDocumento(){
    HiloDocumento hilo=new HiloDocumento(this.ruta);
   hilo.start();
    }
    
    private void adionarMedidasTabla() throws DocumentException {
        float[] medidaCeldas = {1.8f, 2f, 2f, 2f, 2f,2f,2f};
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
        documento.add(new Paragraph("REPORTE DE VENTAS INDIVIDUAL"));      
        String fecha = formatoFecha();
        documento.add(new Paragraph("Fecha de generacion de reporte: " + fecha));
        if(lista.isEmpty()){
         documento.add(new Paragraph("No existen ventas realizadas por el usuario: "+this.persona.getCodigoNombre()));
         return;
        }
         documento.add(new Paragraph("Ventas realizadas en el rango de fechas : " + this.fechaIncial+"  al "+this.fechaFinal));
         this.persona=this.lista.get(0).getPersona();
       
       documento.add(new Paragraph("Ruta: "+this.rutaUsuario+"        Moneda: " + moneda));
        documento.add(new Paragraph("Documento de identificacion :"+this.persona.getNuip()+"            codigo: "+this.persona.getCodigo()));
        documento.add(new Paragraph("Usuario: "+this.persona.getNombre()+" "+this.persona.getApellido()));
        documento.add(new Paragraph("\n"));
    }

    private void añadirRegistro(VentaDto dto, PdfPTable tabla) {
         Font fuente= new Font();
        fuente.setSize(10);
        
           PdfPCell parrafoz = new PdfPCell(new Paragraph(dto.getFecha() + "",fuente));
        tabla.addCell(parrafoz);
       
        
        PdfPCell parrafo3 = new PdfPCell(new Paragraph(dto.getVentaFormato() + "",fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);

        PdfPCell parrafo4 = new PdfPCell(new Paragraph(dto.getComisionFormato() + "",fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);

        PdfPCell parrafo5 = new PdfPCell(new Paragraph(dto.getAbonoFormato() + "",fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        PdfPCell parrafo6 = new PdfPCell(new Paragraph(dto.getRecaudoFormato() + "",fuente));
        parrafo6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo6);

        PdfPCell parrafo7 = new PdfPCell(new Paragraph(dto.getSaldoFormato() + "",fuente));
        parrafo7.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo7);
        
        PdfPCell parrafo8 = new PdfPCell(new Paragraph(dto.getnTicketFormato() + "",fuente));
        parrafo8.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo8);

        ventas += dto.getVenta();
        comisiones += dto.getComision();
        abonos += dto.getRecaudo();
        saldos += dto.getSaldo();
        this.abonado +=dto.getAbono();
        this.totalTicket+=dto.getnTicket();
    }

    /**
     * da forrmato a las columnas
     *
     * @param tabla
     * @return
     */
    private PdfPTable formatoColumna(PdfPTable tabla) {
        PdfPCell columna[] = new PdfPCell[8];
        Font fuente= new Font();
        fuente.setSize(10);
        
          columna[0] = new PdfPCell(new Paragraph("Fecha",fuente));
        columna[0].setBackgroundColor(BaseColor.GRAY);
        
   

        columna[1] = new PdfPCell(new Paragraph("Ventas",fuente));
        columna[1].setBackgroundColor(BaseColor.GRAY);

        columna[2] = new PdfPCell(new Paragraph("Comision",fuente));
        columna[2].setBackgroundColor(BaseColor.GRAY);

        columna[3] = new PdfPCell(new Paragraph("Abonos",fuente));
        columna[3].setBackgroundColor(BaseColor.GRAY);

          columna[4] = new PdfPCell(new Paragraph("Neto",fuente));
        columna[4].setBackgroundColor(BaseColor.GRAY);
        
        columna[5] = new PdfPCell(new Paragraph("Saldo",fuente));
        columna[5].setBackgroundColor(BaseColor.GRAY);
        
          columna[6] = new PdfPCell(new Paragraph("Ticket",fuente));
        columna[6].setBackgroundColor(BaseColor.GRAY);

        tabla.addCell(columna[0]);
        tabla.addCell(columna[1]);
        tabla.addCell(columna[2]);
        tabla.addCell(columna[3]);
        tabla.addCell(columna[4]);
        tabla.addCell(columna[5]);
        tabla.addCell(columna[6]); 
     
       
         

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
