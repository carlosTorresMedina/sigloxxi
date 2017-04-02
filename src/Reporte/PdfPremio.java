/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import ModeloPremio.PremioDto;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author carlos
 */
public class PdfPremio {

    private File directorio;
    private String ruta;

    private Document documento;
    private FileOutputStream ficheroPdf;
    private int totalPremio = 0;
    private int totalRegistros = 0;
    private String moneda;

    //fecha de consulta
    private String fechaConsulta;

    private ArrayList<PremioDto> lista;
    private PdfPTable tabla;

    public PdfPremio() {
        this.ruta = "c:\\Reportes\\ReportesPremios\\";
        this.directorio = new File(this.ruta);
        this.directorio.mkdirs();
    }

    public boolean generarPDFFactura(ArrayList<PremioDto> premios, String fecha, String moneda) {

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
        this.tabla = new PdfPTable(6);
        this.adionarMedidasTabla();
        for (PremioDto dto : lista) {
            this.totalRegistros++;
            this.añadirRegistro(dto, tabla);
        }
        this.generarFilaTotales();
    }

    private void generarFilaTotales() throws DocumentException {
        Font fuente = new Font();
        fuente.setSize(10);

        PdfPCell celdaFinal = new PdfPCell(new Paragraph("Totales", fuente));
        celdaFinal.setBackgroundColor(BaseColor.GRAY);
        celdaFinal.setColspan(5);

        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        tabla.addCell(celdaFinal);
        PdfPCell parrafo1 = new PdfPCell(new Paragraph(formatoNumero.format(this.totalPremio)));
        parrafo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo1);

        documento.add(tabla);

        documento.add(new Paragraph("Cantidad de registros: " + this.totalRegistros, fuente));
    }

    private void abrirDocumento() {
        HiloDocumento hilo = new HiloDocumento(this.ruta);
        hilo.start();
    }

    private void adionarMedidasTabla() throws DocumentException {
        float[] medidaCeldas = {1.8f, 1f, 2f, 2.8f, 1.7f, 2f};
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
        documento.add(new Paragraph("REPORTE DE PREMIOS"));
        String fecha = formatoFecha();
        documento.add(new Paragraph("Fecha de generacion de reporte: " + fecha));
        documento.add(new Paragraph("Ventas realizadas en la fecha de " + this.fechaConsulta));
        if (!this.lista.isEmpty()) {
            documento.add(new Paragraph("Moneda: " + moneda + "                 Numero ganador: " + lista.get(0).getNumeroGanador()));
        } else {
            documento.add(new Paragraph("Moneda: " + moneda + "                 Numero ganador: No se programo la loteria ese dia"));
        }

        documento.add(new Paragraph("\n"));
    }

    private void añadirRegistro(PremioDto dto, PdfPTable tabla) {
        this.totalPremio += dto.getDetPremio().getPremio();

        Font fuente = new Font();
        fuente.setSize(10);
        PdfPCell parrafo = new PdfPCell(new Paragraph(dto.getSerial() + "", fuente));
        tabla.addCell(parrafo);

        PdfPCell parrafo2 = new PdfPCell(new Paragraph(dto.getPersona().getCodigo() + "", fuente));
        tabla.addCell(parrafo2);

        PdfPCell parrafo6 = new PdfPCell(new Paragraph(dto.getDetPremio().getLoteria().getCodigoNombre() + "", fuente));
        tabla.addCell(parrafo6);

        PdfPCell parrafo7 = new PdfPCell(new Paragraph(dto.getDetPremio().getTipoPremio().getTipoNombre() + "", fuente));
        tabla.addCell(parrafo7);

        PdfPCell parrafo8 = new PdfPCell(new Paragraph(dto.getDetPremio().getValorFormato() + "", fuente));
        parrafo8.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo8);

        PdfPCell parrafo9 = new PdfPCell(new Paragraph(dto.getDetPremio().getPremioFormato() + "", fuente));
        parrafo9.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(parrafo9);

    }

    /**
     * da forrmato a las columnas
     *
     * @param tabla
     * @return
     */
    private PdfPTable formatoColumna(PdfPTable tabla) {
        PdfPCell columna[] = new PdfPCell[6];
        Font fuente = new Font();
        fuente.setSize(10);
        columna[0] = new PdfPCell(new Paragraph("Serial", fuente));
        columna[0].setBackgroundColor(BaseColor.GRAY);

        columna[1] = new PdfPCell(new Paragraph("Vend", fuente));
        columna[1].setBackgroundColor(BaseColor.GRAY);

        columna[2] = new PdfPCell(new Paragraph("Loteria", fuente));
        columna[2].setBackgroundColor(BaseColor.GRAY);

        columna[3] = new PdfPCell(new Paragraph("Tipo", fuente));
        columna[3].setBackgroundColor(BaseColor.GRAY);

        columna[4] = new PdfPCell(new Paragraph("Valor", fuente));
        columna[4].setBackgroundColor(BaseColor.GRAY);

        columna[5] = new PdfPCell(new Paragraph("Premio", fuente));
        columna[5].setBackgroundColor(BaseColor.GRAY);

        tabla.addCell(columna[0]);
        tabla.addCell(columna[1]);
        tabla.addCell(columna[2]);
        tabla.addCell(columna[3]);
        tabla.addCell(columna[4]);
        tabla.addCell(columna[5]);

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
