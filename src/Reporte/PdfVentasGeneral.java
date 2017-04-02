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
public class PdfVentasGeneral {

    private File directorio;
    private String ruta;

    private Document documento;
    private FileOutputStream ficheroPdf;

    private int ventasVenezuela = 0;
    private int abonadoVenezuela = 0;
    private int comisionesVenezuela = 0;
    private int abonosVenezuela = 0;
    private int saldosVenezuela = 0;
    private int totalTicketVenezuela = 0;

    private int ventasColombia = 0;
    private int abonadoColombia = 0;
    private int comisionesColombia = 0;
    private int abonosColombia = 0;
    private int saldosColombia = 0;
    private int totalTicketColombia = 0;

    private int totalRegistros = 0;

    private String fechaIncial;
    private String fechaFinal;

    private ArrayList<ArrayList<VentaDto>> listaPesos;
    private ArrayList<ArrayList<VentaDto>> listaBolivar;
    private PdfPTable tabla;

    private String forma;

    private int FinalaboonadoVenezuela = 0;
    private int FinalcomisionesVenezuela = 0;
    private int FinalabonosVenezuela = 0;
    private int FinalsaldosVenezuela = 0;
    private int FinalVentasVenezuela = 0;
    private int FinaltickeVenezuela = 0;

    private int FinalaboonadoColombia = 0;
    private int FinalcomisionesColombia = 0;
    private int FinalabonosColombia = 0;
    private int FinalsaldosColombia = 0;
    private int FinalVentasColombia = 0;
    private int FinaltickeColombia = 0;

    public PdfVentasGeneral() {
        this.ruta = "c:\\Reportes\\ReportesVentasGeneral\\";
        this.directorio = new File(this.ruta);
        this.directorio.mkdirs();
    }

    /**
     * genera el documento pdf de reportes
     *
     * @param listap
     * @param fechaInicial
     * @param fechaFinal
     * @param moneda
     * @param ruta
     * @param tipo
     * @param forma
     * @return
     */
    public boolean generarPDFFactura(ArrayList<VentaDto> listaP, ArrayList<VentaDto> listaB, String fechaInicial, String fechaFinal, String forma) {

        boolean dato = this.generarArchvo();

        this.fechaIncial = fechaInicial;
        this.fechaFinal = fechaFinal;

        this.forma = forma;
        this.listaPesos = convertirLista(listaP);
        this.listaBolivar = convertirLista(listaB);

     

        try {
            documento.open();
            this.generarEncabezado();
            this.generarTablaVenezuela();
            documento.newPage();
            this.generarTablaColombia();
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

    private void generarTablaVenezuela() throws DocumentException {
          documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Moneda:  Bolivares"));
            documento.add(new Paragraph("\n"));
        this.tabla = new PdfPTable(8);
            this.adionarMedidasTabla();
        for (ArrayList<VentaDto> list : listaBolivar) {
          
            
            for (VentaDto dto : list) {

                this.añadirRegistro(dto);
            }
             this.generarFilaTotalesVenezuela(list.get(0).getFecha());
            

            this.FinalVentasVenezuela += ventasVenezuela;
            this.FinaltickeVenezuela += totalTicketVenezuela;
            this.FinalsaldosVenezuela += saldosVenezuela;
            this.FinalaboonadoVenezuela += this.abonadoVenezuela;
            this.FinalabonosVenezuela += this.abonosVenezuela;
            this.FinalcomisionesVenezuela += this.comisionesVenezuela;


            this.totalTicketVenezuela = 0;
            this.saldosVenezuela = 0;
            this.comisionesVenezuela = 0;
            this.abonadoVenezuela = 0;
            this.abonosVenezuela = 0;
            this.ventasVenezuela = 0;

           

        }
        this.generarFilasTotalesFinalVenezuela();

    }
    
     private void generarTablaColombia() throws DocumentException {
          documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Moneda:  Pesos"));
            documento.add(new Paragraph("\n"));
            this.tabla = new PdfPTable(8);
            this.adionarMedidasTabla();
        for (ArrayList<VentaDto> list : listaPesos) {
          
            
            for (VentaDto dto : list) {

                this.añadirRegistro(dto);
            }
         
            this.generarFilaTotalesColombia(list.get(0).getFecha());

        

            this.FinalVentasColombia += ventasColombia;
            this.FinaltickeColombia += totalTicketColombia;
            this.FinalsaldosColombia += saldosColombia;
            this.FinalaboonadoColombia += this.abonadoColombia;
            this.FinalabonosColombia += this.abonosColombia;
            this.FinalcomisionesColombia += this.comisionesColombia;

         

            this.totalTicketColombia = 0;
            this.saldosColombia = 0;
            this.comisionesColombia = 0;
            this.abonadoColombia = 0;
            this.abonosColombia = 0;
            this.ventasColombia = 0;

        }
        this.generarFilasTotalesFinalColombia();

    }

     private void generarFilasTotalesFinalColombia() throws DocumentException {
        
        
        Font fuente = new Font();
        fuente.setSize(10);

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Totales"));
        celdaFinal.setBackgroundColor(BaseColor.GRAY);
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        celdaFinal.setColspan(2);
        tabla.addCell(celdaFinal);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalVentasColombia), fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);
        PdfPCell parrafo2 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalcomisionesColombia), fuente));
        parrafo2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo2);
        PdfPCell parrafo3 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalaboonadoColombia), fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);
        PdfPCell parrafo4 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalabonosColombia), fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);
        PdfPCell parrafo5 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalsaldosColombia), fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        PdfPCell parrafo6 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinaltickeColombia), fuente));
        parrafo6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo6);

        documento.add(tabla);
        
        
        
         documento.add(new Paragraph("\n"));

    }
     
     
    private void generarFilasTotalesFinalVenezuela() throws DocumentException {
        
        
        Font fuente = new Font();
        fuente.setSize(10);

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Totales"));
        celdaFinal.setBackgroundColor(BaseColor.GRAY);
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        celdaFinal.setColspan(2);
        tabla.addCell(celdaFinal);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalVentasVenezuela), fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);
        PdfPCell parrafo2 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalcomisionesVenezuela), fuente));
        parrafo2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo2);
        PdfPCell parrafo3 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalaboonadoVenezuela), fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);
        PdfPCell parrafo4 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalabonosVenezuela), fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);
        PdfPCell parrafo5 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinalsaldosVenezuela), fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        PdfPCell parrafo6 = new PdfPCell(new Paragraph(formatoNumero.format(this.FinaltickeVenezuela), fuente));
        parrafo6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo6);

        documento.add(tabla);
        
        
        
         documento.add(new Paragraph("\n"));

    }

    private void generarFilaTotalesVenezuela(String fecha) throws DocumentException {
        Font fuente = new Font();
        fuente.setSize(10);

        PdfPCell celdaFinal = new PdfPCell(new Paragraph(fecha));
       
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        celdaFinal.setColspan(2);
        tabla.addCell(celdaFinal);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.ventasVenezuela), fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);
        PdfPCell parrafo2 = new PdfPCell(new Paragraph(formatoNumero.format(this.comisionesVenezuela), fuente));
        parrafo2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo2);
        PdfPCell parrafo3 = new PdfPCell(new Paragraph(formatoNumero.format(this.abonadoVenezuela), fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);
        PdfPCell parrafo4 = new PdfPCell(new Paragraph(formatoNumero.format(this.abonosVenezuela), fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);
        PdfPCell parrafo5 = new PdfPCell(new Paragraph(formatoNumero.format(this.saldosVenezuela), fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        PdfPCell parrafo6 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalTicketVenezuela), fuente));
        parrafo6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo6);

//        documento.add(tabla);
    }

    private void generarFilaTotalesColombia(String fecha) throws DocumentException {
        Font fuente = new Font();
        fuente.setSize(10);

        PdfPCell celdaFinal = new PdfPCell(new Paragraph(fecha));
       
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        celdaFinal.setColspan(2);
        tabla.addCell(celdaFinal);

        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.ventasColombia), fuente));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);
        PdfPCell parrafo2 = new PdfPCell(new Paragraph(formatoNumero.format(this.comisionesColombia), fuente));
        parrafo2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo2);
        PdfPCell parrafo3 = new PdfPCell(new Paragraph(formatoNumero.format(this.abonadoColombia), fuente));
        parrafo3.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo3);
        PdfPCell parrafo4 = new PdfPCell(new Paragraph(formatoNumero.format(this.abonosColombia), fuente));
        parrafo4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo4);
        PdfPCell parrafo5 = new PdfPCell(new Paragraph(formatoNumero.format(this.saldosColombia), fuente));
        parrafo5.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo5);

        PdfPCell parrafo6 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalTicketColombia), fuente));
        parrafo6.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo6);

//        documento.add(tabla);

    }

    private void abrirDocumento() {
        HiloDocumento hilo = new HiloDocumento(this.ruta);
        hilo.start();
    }

    private void adionarMedidasTabla() throws DocumentException {
        float[] medidaCeldas = {1.2f, 3.5f, 2f, 2f, 2f, 2f, 2f, 2f};

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
        Date fecha = new Date();
        ruta = ruta + fecha.getHours() + fecha.getMinutes() + fecha.getSeconds() + ".pdf";
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
        documento.add(new Paragraph("REPORTE DE VENTAS " + forma));
        String fecha = formatoFecha();
        documento.add(new Paragraph("Fecha de generacion de reporte: " + fecha));
        documento.add(new Paragraph("Ventas realizadas en el rango de fechas : " + this.fechaIncial + "  al " + this.fechaFinal));
        documento.add(new Paragraph("\n"));
      
    }

    private void añadirRegistro(VentaDto dto) {

        if (dto.getMoneda().equals("Bolivares")) {

            ventasVenezuela += dto.getVenta();
            comisionesVenezuela += dto.getComision();
            abonosVenezuela += dto.getRecaudo();
            saldosVenezuela += dto.getSaldo();
            this.abonadoVenezuela += dto.getAbono();
            this.totalTicketVenezuela += dto.getnTicket();

        } else {

            ventasColombia += dto.getVenta();
            comisionesColombia += dto.getComision();
            abonosColombia += dto.getRecaudo();
            saldosColombia += dto.getSaldo();
            this.abonadoColombia += dto.getAbono();
            this.totalTicketColombia += dto.getnTicket();

        }

    }

    /**
     * da forrmato a las columnas
     *
     * @param tabla
     * @return
     */
    private PdfPTable formatoColumna(PdfPTable tabla) {
        PdfPCell columna[] = new PdfPCell[7];
        Font fuente = new Font();
        fuente.setSize(10);

        columna[0] = new PdfPCell(new Paragraph("Fecha", fuente));
        columna[0].setBackgroundColor(BaseColor.GRAY);
        columna[0].setColspan(2);

        columna[1] = new PdfPCell(new Paragraph("Ventas", fuente));
        columna[1].setBackgroundColor(BaseColor.GRAY);

        columna[2] = new PdfPCell(new Paragraph("Comision", fuente));
        columna[2].setBackgroundColor(BaseColor.GRAY);

        columna[3] = new PdfPCell(new Paragraph("Abonos", fuente));
        columna[3].setBackgroundColor(BaseColor.GRAY);

        columna[4] = new PdfPCell(new Paragraph("Neto", fuente));
        columna[4].setBackgroundColor(BaseColor.GRAY);

        columna[5] = new PdfPCell(new Paragraph("Saldo", fuente));
        columna[5].setBackgroundColor(BaseColor.GRAY);

        columna[6] = new PdfPCell(new Paragraph("Ticket", fuente));
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

    /**
     * metodo que divide la lista por fechas. en la pocision 0 del array esta la
     * fecha y en la segunda pocision esta el array de ventas en esa fecha
     *
     * @param listamn
     *
     * @return
     */
    private ArrayList<ArrayList<VentaDto>> convertirLista(ArrayList<VentaDto> lista) {
        ArrayList<ArrayList<VentaDto>> listaFinal = new ArrayList();
        ArrayList<VentaDto> div = new ArrayList();
        if (lista.isEmpty()) {
            return listaFinal;
        }

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) != null) {
                for (int j = 0; j < lista.size(); j++) {
                    if (lista.get(j) != null) {

                        if (lista.get(i).getFecha().equals(lista.get(j).getFecha())) {
                            div.add(lista.get(j));
                            if (i != j) {
                                lista.set(j, null);

                            }
                        }
                    }
                }
                listaFinal.add(div);
                div = new ArrayList();
            }
        }

        return listaFinal;
    }
}
