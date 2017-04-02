/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fachada;

import InformeGeneral.GestorInformeGeneral;
import InformeGeneral.InformeGeneralDto;
import ModeloLoteria.GestorLoteria;
import ModeloPlanLoteria.GestorPlanLoteria;
import ModeloPremio.GestorPremio;
import ModeloTipoPremio.GestorTipoPremio;
import ModeloLoteria.LoteriaDto;
import ModeloPlanLoteria.PlanLoteriaDto;
import ModeloPremio.PremioDto;
import ModeloPremio.TicketDto;
import ModeloTipoPremio.TipoPremioDto;
import ModeloVentas.Fecha;
import ModeloVentas.GestorPersona;
import ModeloVentas.GestorVentas;
import ModeloVentas.PersonaDto;
import ModeloVentas.VentaDto;
import ModuloPrestamos.GestorPrestamos;
import ModuloPrestamos.PrestamoDto;
import Recurso.CopiaSeguridad;
import Reporte.PdfInformePrestamos;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author carlos puerta principal al sistema
 */
public class Fachada {

   

    public ArrayList<PremioDto> consultarPremioXFechaXSerail(String fecha, String serial) {

        ArrayList<PremioDto> lista= GestorPremio.getInstance().consultarPremiosXFechaXSeriall(fecha, serial);
          return GestorPremio.getInstance().adicionarNumeroLoteria(lista);
    }

    /**
     * Consulta que la loteria ya tenga registrado un numero jugado.
     *
     * @param codigo
     * @param fecha
     * @return
     */
    public boolean validarLoteriaJugada(int codigo, String fecha) {
        ArrayList<PlanLoteriaDto> lista;

        lista = GestorPlanLoteria.getInstance().consultarPlanLoteriaFechaCodigo(fecha, codigo);

        for (PlanLoteriaDto plan : lista) {

            if (!plan.getNumero().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * este metodo lista los planes loterias que ya se han registrado numero en
     * la fecha estipulada.
     *
     * @return
     */
    public ArrayList<LoteriaDto> listarPlanLoteriasNumero(String fecha) {

        return GestorPlanLoteria.getInstance().ListarPlanLoteriaNumero(fecha);

    }

    /**
     * modifica el abono de una venta especifica
     *
     * @param dto
     * @return
     */
    public boolean modificarVentaAbono(VentaDto dto) {
        GestorVentas g = new GestorVentas();
        return g.modificarVentaAbono(dto);
    }

    /**
     * metodo que consulta una venta especifica.
     *
     * @param dto
     * @return
     */
    public VentaDto consultarVentaEspecifica(VentaDto dto) {
        GestorVentas g = new GestorVentas();
        return g.consultarVentaEspecifica(dto);
    }

    /**
     * metdo que genera el archivo pdf conlos datos del informe general
     *
     * @param fechaI
     * @param fechaF
     * @param ruta
     * @return
     */
    public boolean generarInformeGeneral(String fechaI, String fechaF, String ruta) {
        GestorInformeGeneral gestor = GestorInformeGeneral.getInstance();
        ArrayList<InformeGeneralDto> NochePesos = gestor.listaNochePesos(fechaI, fechaF);
        ArrayList<InformeGeneralDto> DiaPesos = gestor.listaDiaPesos(fechaI, fechaF);
        ArrayList<InformeGeneralDto> NocheBolivares = gestor.listaNocheBolivares(fechaI, fechaF);
        ArrayList<InformeGeneralDto> DiaBolivares = gestor.listaDiaBolivares(fechaI, fechaF);

        Reporte.PdfInformeGeneral pdf = new Reporte.PdfInformeGeneral();
        return pdf.generarPDFFactura(fechaI, fechaF, NochePesos, DiaPesos, NocheBolivares, DiaBolivares);

    }

    
     /**
     * metdo que genera el archivo pdf conlos datos del informe general
     *
     * @param fechaI
     * @param fechaF
     * @param ruta
     * @return
     */
    public boolean generarInformeGeneralNacionalidad(String fechaI, String fechaF, String ruta,String nacionalidad) {
        GestorInformeGeneral gestor = GestorInformeGeneral.getInstance();
        ArrayList<InformeGeneralDto> NochePesos = gestor.listaNochePesosNacionalidadTipo(fechaI, fechaF,nacionalidad);
        ArrayList<InformeGeneralDto> DiaPesos = gestor.listaDiaPesosNacionalidadTipo(fechaI, fechaF,nacionalidad);
        ArrayList<InformeGeneralDto> NocheBolivares = gestor.listaNocheBolivaresNacionalidadTipo(fechaI, fechaF,nacionalidad);
        ArrayList<InformeGeneralDto> DiaBolivares = gestor.listaDiaBolivaresNacionalidadTipo(fechaI, fechaF,nacionalidad);

        Reporte.PdfVentaNacionalidad pdf = new Reporte.PdfVentaNacionalidad();
        return pdf.generarPDFFactura(fechaI, fechaF, NochePesos, DiaPesos, NocheBolivares, DiaBolivares,nacionalidad);

    }
    
    
    
    /**
     * metodo que genera un arrayList con los datos que debe tener el reporte
     *
     * @param mes
     * @param ano
     * @param moneda
     * @return
     */
    public ArrayList<VentaDto> generarReporte(int mes, int ano, String moneda, String ruta, String tipo) {
        GestorVentas venta = new GestorVentas();
        Fecha fecha = new Fecha();
        int limites[] = fecha.retornaLimitesFecha(mes);
        String fechai = fecha.generarFecha(ano, mes, limites[0]);
        String fechaf = fecha.generarFecha(ano, mes, limites[1]);

        ArrayList<VentaDto> lista = venta.consultarVentasXRangoFechaResumen(fechai, fechaf, moneda, ruta, tipo);
        lista = venta.añadirSaldoAnterior(lista, mes);

        return lista;
    }

    /**
     * metodo que genera archivo pdf para el reporte de ventas chance
     *
     * @param premios
     * @param absolutePath
     * @param fecha
     * @param toString
     * @return
     */
    public boolean generarPdfVentasChance(ArrayList<TicketDto> premios, String absolutePath, String fecha, String moneda) {
        Reporte.PdfChance pdf = new Reporte.PdfChance();
        return pdf.generarPDFFactura(premios, fecha, moneda);
    }

    /**
     * consulta la cantidad de chances que ha vendido cada persona en el sistema
     *
     * @param fecha
     * @return
     */
    public ArrayList<TicketDto> consultarVentasChance(String fecha, String moneda) {

        return GestorPremio.getInstance().consultarVentasChance(fecha, moneda);

    }

    /**
     * realiza el proceso de escrutinio del plan loteria
     *
     * @param dto
     * @return
     */
    public boolean escrutarLoteria(PlanLoteriaDto dto) {

        boolean valor = false;
        // retrono los items ganadores en formato premio
        ArrayList<PremioDto> ganadores = GestorPremio.getInstance().listarPremiosGanadores(dto);
        if (ganadores.isEmpty()) {
            return true;
        }

//        // calculo el premio de cada uno 
        for (PremioDto pre : ganadores) {

            pre = this.calculaPremioNombre(pre);

            //valido si ya existe el serial registrado
            valor = GestorPremio.getInstance().validarExistenciaPremio(pre);

            if (!valor) {
                valor = this.registrarPremio(pre);
            } else {
                valor = this.agregarDetPremio(pre);
            }
        }
        return valor;
    }

    public int calcularPremio(PremioDto dto) {
       
        int premio = GestorTipoPremio.getInstance().CalcularPremioBaseTipo(dto);
        return premio;
    }

    /**
     * este base es utilizado cuando no se conoce el tipo de premio si no solo
     * el nombre
     *
     * @param dto
     * @return
     */
    public PremioDto calculaPremioNombre(PremioDto dto) {
       
       return GestorTipoPremio.getInstance().CalcularPremioBaseTipoNombre(dto);
       
    }

    /**
     * registra un premio en el sistema
     *
     * @param dto
     * @return
     */
    public boolean registrarPremio(PremioDto dto) {

        return GestorPremio.getInstance().registrarPremio(dto);
    }

    /**
     * agrega un determinado det premio al premio
     *
     * @param dto
     * @return
     */
    public boolean agregarDetPremio(PremioDto dto) {

        return GestorPremio.getInstance().agregarDetPremio(dto);
    }

    /**
     * registra el numero y la serie del plan loteria
     *
     * @param dto
     * @return
     */
    public boolean registrarNumeroSeriePlanLoteria(PlanLoteriaDto dto) {

        return GestorPlanLoteria.getInstance().registrarNumeroSeriePlanLoteria(dto);
    }

    /**
     * crea copia de seguridad de la base de datos
     *
     * @param ruta
     */
    public void crearCopiaSeguridad(String ruta) {
        CopiaSeguridad copia = new CopiaSeguridad();
        copia.crearCopiaSeguridad(ruta);
    }

    /*
     metodo que modifica la fecha y la hora de un plan loteria
     */
    public boolean ModificarHoraFechaPlanLoteria(int codigo, String fechaL, String fechaHora) {
        return GestorPlanLoteria.getInstance().modificarHoraFechaPlanLoteria(codigo, fechaL, fechaHora);
    }

    /**
     * modifica un plan loteria registrado en el sistema
     *
     * @param dto
     * @return
     */
    public boolean modificarPlanLoteria(String fecha, int codigo, PlanLoteriaDto dto) {

        return GestorPlanLoteria.getInstance().modificarPlanLoteria(fecha, codigo, dto);
    }

    public void actualizarProcesoEscrutinio(PlanLoteriaDto dto) {

        GestorPlanLoteria.getInstance().actualizarProcesoEscrutinio(dto);
    }

    /**
     * metodo que lista todos los planes loterias registrados en el sitema
     *
     * @return
     */
    public ArrayList<LoteriaDto> listarPlanLoteria() {

        return GestorPlanLoteria.getInstance().listarPlanLoteria();
    }

    /**
     * metodo que lista todos los planes loterias por fecha
     *
     * @param fecha
     * @return
     */
    public ArrayList<LoteriaDto> listarPlanLoteriaXFecha(String fecha) {

        return GestorPlanLoteria.getInstance().listarPlanLoteriaXFecha(fecha);
    }

    /**
     * lista el plan loteria registrado en el sistema
     *
     * @param parseInt
     * @return
     */
    public ArrayList<PlanLoteriaDto> listarPlanLoteriaFechaCodigo(String fecha, int parseInt) {

        return GestorPlanLoteria.getInstance().consultarPlanLoteriaFechaCodigo(fecha, parseInt);
    }

    /**
     * metodo que lista los premios segun su fecha y codigo
     *
     * @param fecha
     * @param serial
     * @return
     */
    public ArrayList<PremioDto> listarPremioXFechaCodigo(String fecha, String serial) {

        return GestorPremio.getInstance().listarPremioXFechaCodigo(fecha, serial);
    }

    /**
     * metodo que elimina un premio segun su fecha y su hora
     *
     * @param codigo
     * @param fecha
     * @return
     */
    public boolean eliminarPremio(String codigo, String fecha) {

        return GestorPremio.getInstance().eliminarPremio(codigo, fecha);
    }

    /**
     * metodo que consulta los premios registrados en el sistema
     *
     * @return
     */
    public ArrayList<PremioDto> consultarPremios() {

        return GestorPremio.getInstance().consultarPremios();

    }

    /**
     * metodo que consulta el total de un premio segun su fecha y su modena
     *
     * @param fecha
     * @param moneda
     * @return
     */
    public long consultarTotalPagoFechaMoneda(String fecha, String moneda) {

        return GestorPremio.getInstance().consultarTotalPagoFechaMoneda(fecha, moneda);
    }

    /**
     * consulta los premios por la moneda fecha y por codigo de loteria.
     *
     * @param fecha
     * @param moneda
     * @param codigo
     * @return
     */
    public ArrayList<PremioDto> consultarPremiosXFechaXMoneda(String fecha, String moneda, int codigo) {

        ArrayList<PremioDto> lista = GestorPremio.getInstance().consultarPremiosXFechaXMoneda(fecha, moneda, codigo);
        return GestorPremio.getInstance().adicionarNumeroLoteria(lista);
    }

    /**
     * lista todos los vendedores registrados en el ssitema
     *
     * @return
     */
    public ArrayList<PersonaDto> listarVendedores() {
        
        return GestorPersona.getInstance().listarVendedores();

    }

    /**
     * modifica un tpo premio en la base de datos
     *
     * @param tipoA
     * @param dto
     * @return
     */
    public boolean modificarTipoPremio(int tipoA, TipoPremioDto dto) {
       
        return GestorTipoPremio.getInstance().modificarTipoPremio(tipoA, dto);
    }

    /**
     * lista todos los premios registrados en el sistema
     *
     * @return
     */
    public ArrayList<TipoPremioDto> listarTipoPremios() {
        
        return GestorTipoPremio.getInstance().listarTipoPremios();
    }

    /**
     * eliminar tipo premio
     *
     * @param tipo
     * @return
     */
    public boolean eliminarTipoPremio(int tipo) {
        
        return GestorTipoPremio.getInstance().eliminarTipoPremio(tipo);
    }

    /**
     * lista los premios segun su tipo
     *
     * @param tipo
     * @return
     */
    public ArrayList<TipoPremioDto> listarTipoPremioXTipo(int tipo) {
        
        return GestorTipoPremio.getInstance().listarTipoPremioXTipo(tipo);
    }

    /**
     * registra tipo premio
     *
     * @param dto
     * @return
     */
    public boolean registrarTipoPremio(TipoPremioDto dto) {
   
        return GestorTipoPremio.getInstance().registrarTipoPremio(dto);
    }

    /**
     * elimina un plan de loterias
     *
     * @return
     */
    public boolean eliminarPlanLoteria(int codigoLoteria, String fechaPlan) {

        return GestorPlanLoteria.getInstance().eliminarPlanLoteria(codigoLoteria, fechaPlan);

    }

    /**
     * consulta planes de loteria segun la loteria
     *
     * @param dto
     * @return
     */
    public LoteriaDto consultarPlanLoteriaXLoteria(LoteriaDto dto) {

        return GestorPlanLoteria.getInstance().consultarPlanLoteriaXLoteria(dto);
    }

    /**
     * registra un plan loteria en el sistema
     *
     * @param dto
     * @return
     */
    public String registrarPlanLoteria(LoteriaDto dto) {

        return GestorPlanLoteria.getInstance().registrarPlanLoteria(dto);
    }

    /**
     * elimina una determinada loteria segun el codigo
     *
     * @param codigo
     * @return
     */
    public boolean eliminarLoteria(int codigo) {

        return GestorLoteria.getInstance().eliminarLoteria(codigo);
    }

    /**
     * lista todas las loterias registradas en el sistema
     *
     * @return
     */
    public ArrayList<LoteriaDto> listarLoterias() {

        return GestorLoteria.getInstance().listarLoterias();
    }

    /**
     * lista las loterias segun el codigo
     *
     * @param codigo
     * @return
     */
    public ArrayList<LoteriaDto> listarLoteriaXCodigo(int codigo) {

        return GestorLoteria.getInstance().listarLoteriaXCodigo(codigo);
    }

    /**
     * lista las loterias segun el nombre
     *
     * @param nombre
     * @return
     */
    public ArrayList<LoteriaDto> listarLoteriaXNombre(String nombre) {

        return GestorLoteria.getInstance().listarLoteriaXNombre(nombre);
    }

    public boolean modificarLoteria(int codigoA, LoteriaDto dto) {

        return GestorLoteria.getInstance().modificarLoteria(codigoA, dto);
    }

    /**
     * metodo que permite registrar una loteria en el sistema
     *
     * @param dto
     * @return
     */
    public boolean registrarLoteria(LoteriaDto dto) {

        return GestorLoteria.getInstance().registrarLoteria(dto);
    }

    /**
     * metodo que genera archivo pdf para el reporte de ventas
     *
     * @param rutaArchivo
     * @param ruta
     * @param moneda
     * @return
     */
    public boolean generarPdfVentasResumen(String rutaArchivo, String fechaInicial, String fechaFinal, String moneda, String ruta, String tipo) {
        Reporte.PdfVenta pdf = new Reporte.PdfVenta();

        ArrayList<VentaDto> lista = this.consultarVentasRangoFechaResumen(fechaInicial, fechaFinal, moneda, ruta, tipo);
        return pdf.generarPDFFactura(lista, fechaInicial, fechaFinal, moneda, ruta, tipo, "RESUMEN");
    }

    public boolean generarPdfVentasEmpleado(String absolutePath, String fechaInicial, String fechaFinal, String moneda, String ruta, PersonaDto persona, ArrayList<VentaDto> listaVentas) {
        Reporte.PdfVentaPersona pdf = new Reporte.PdfVentaPersona();

        return pdf.generarPDFFactura(listaVentas, fechaInicial, fechaFinal, moneda, ruta, persona);
    }

    public boolean generarPdfVentasDetallado(String rutaArchivo, String fechaInicial, String fechaFinal, String moneda, String ruta, String tipo) {

        Reporte.PdfVentaDetalle pdf = new Reporte.PdfVentaDetalle();
        ArrayList<VentaDto> lista = this.consultarVentasRangoFechaDetallado(fechaInicial, fechaFinal, moneda, ruta, tipo);
        return pdf.generarPDFFactura(lista, fechaInicial, fechaFinal, moneda, ruta, tipo, "DETALLADO");
    }
    

    
    /**
     * Requerimiento nuevo
     * 14/11/2016
     * @param rutaArchivo
     * @param fechaInicial
     * @param fechaFinal
     * @param moneda
     * @param ruta
     * @param tipo
     * @return 
     */
     public boolean generarPdfVentasGeneral(String rutaArchivo, String fechaInicial, String fechaFinal) {

        Reporte.PdfVentasGeneral pdf = new Reporte.PdfVentasGeneral();
        ModeloVentas.GestorVentas ges=new ModeloVentas.GestorVentas();
        ArrayList<VentaDto> listaPesos = ges.consultarVentasXRangoFechaGeneralPesos(fechaInicial, fechaFinal);
        ArrayList<VentaDto> listaBolivar=ges.consultarVentasXRangoFechaGeneralBolivares(fechaInicial, fechaFinal);
        return pdf.generarPDFFactura(listaPesos,listaBolivar, fechaInicial, fechaFinal, "GENERAL");
    }

    public boolean generarPdfVentasSaldosPositivos(ArrayList<VentaDto> listaVentas, String rutaArchivo, String mes, String moneda, String ruta, String tipo) {
        Reporte.PdfVentasSaldos pdf = new Reporte.PdfVentasSaldos();

        return pdf.generarPDFFactura(listaVentas, mes, moneda, ruta, tipo, "POSITIVOS");
    }

    public boolean generarPdfVentasSaldosNegativos(ArrayList<VentaDto> listaVentas, String rutaArchivo, String mes, String moneda, String ruta, String tipo) {
        Reporte.PdfVentasSaldos pdf = new Reporte.PdfVentasSaldos();

        return pdf.generarPDFFactura(listaVentas, mes, moneda, ruta, tipo, "NEGATIVOS");
    }

    /**
     * metodo que genera archivo pdf para el reporte de premios
     *
     * @param premios
     * @param absolutePath
     * @param fecha
     * @param toString
     * @return
     */
    public boolean generarPdfPremios(ArrayList<PremioDto> premios, String absolutePath, String fecha, String moneda) {
        Reporte.PdfPremio pdf = new Reporte.PdfPremio();
        return pdf.generarPDFFactura(premios, fecha, moneda);
    }

    /**
     * metodo que genera un arrayList con los datos que debe tener el reporte de
     * ventas en resumen primero se lista todas las ventas segun los parametros
     *
     * @param fechaInicial
     * @param fechaFinal
     * @param moneda
     * @param ruta
     * @param tipo
     * @return
     */
    public ArrayList<VentaDto> consultarVentasRangoFechaResumen(String fechaInicial, String fechaFinal, String moneda, String ruta, String tipo) {
        GestorVentas venta = new GestorVentas();
        ArrayList<VentaDto> lista = venta.consultarVentasXRangoFechaResumen(fechaInicial, fechaFinal, moneda, ruta, tipo);
        return lista;
    }

    public ArrayList<VentaDto> consultarVentasPersonaRangoFechaResumen(String fechaInicial, String fechaFinal, String moneda, String ruta, PersonaDto dto) {
        GestorVentas venta = new GestorVentas();
        ArrayList<VentaDto> lista = venta.consultarVentasPersonaXRangoFechaResumen(fechaInicial, fechaFinal, moneda, ruta, dto);
        return lista;
    }

    public ArrayList<VentaDto> consultarVentasRangoFechaDetallado(String fechaInicial, String fechaFinal, String moneda, String ruta, String tipo) {
        GestorVentas venta = new GestorVentas();
        ArrayList<VentaDto> lista = venta.consultarVentasXRangoFechaDetallado(fechaInicial, fechaFinal, moneda, ruta, tipo);
        return lista;
    }
    
     public ArrayList<VentaDto> consultarVentasRangoFechaNacionalidad(String fechaInicial, String fechaFinal,String nacionalidad) {
        GestorVentas venta = new GestorVentas();
        ArrayList<VentaDto> lista = venta.consultarVentasXRangoFechaNacionalidad(fechaInicial, fechaFinal,nacionalidad);
        return lista;
    }

    public int[] retornaLimitesFecha(int mes) {
        Fecha fecha = new Fecha();
        return fecha.retornaLimitesFecha(mes);
    }

    public int generarMes(String mes) {
        Fecha fecha = new Fecha();
        fecha.setFecha(mes);
        return fecha.getMes();
    }

    /**
     * registra una persona en el sistema
     *
     * @param dto
     * @return
     */
    public boolean registrarPersona(PersonaDto dto) {
     
        return GestorPersona.getInstance().registrarPersona(dto);
    }

    /**
     * lista las personas por la cedula
     *
     * @param cedula
     * @return
     */
    public ArrayList<PersonaDto> listarPersonaXCedula(int cedula) {
        
        return GestorPersona.getInstance().listarPersonaXCedula(cedula);
    }

    /**
     * lista las personas por el codigo
     *
     * @param codigo
     * @return
     */
    public ArrayList<PersonaDto> listarPersonaXCodigo(int codigo) {
        
        return GestorPersona.getInstance().listarPersonaXCodigo(codigo);
    }

    public ArrayList<PersonaDto> listarPersonaXCodigoTipo(int codigo, String tipo) {
       
        return GestorPersona.getInstance().listarPersonaXCodigoTipo(codigo, tipo);
    }

    /**
     * lista todas las personas que estan registradas en el sistema
     *
     * @return
     */
    public ArrayList<PersonaDto> listarPersonas() {
        
        return GestorPersona.getInstance().listarPersonas();
    }

    /**
     * elimina una persona segun la cedula
     *
     * @param cedula
     * @return
     */
    public boolean eliminarPersona(int cedula) {
       
        return GestorPersona.getInstance().eliminarPersona(cedula);
    }

    /**
     * modifica una persona en el sistema
     *
     * @param cedulaAnterior
     * @param dto
     * @return
     */
    public boolean modifcarPersona(int cedulaAnterior, PersonaDto dto) {
  
        return GestorPersona.getInstance().modifcarPersona(cedulaAnterior, dto);
    }

    /**
     * registra una venta en el sistema
     *
     * @param dto
     * @return
     */
    public boolean registrarVenta(VentaDto dto) {
        GestorVentas venta = new GestorVentas();

        return venta.registrarVenta(dto);
    }

    /**
     * consulta las ventas de una fecha especifica.
     *
     * @param fecha
     * @param moneda
     * @param ruta
     * @param tipo
     * @return
     */
    public ArrayList<VentaDto> consultarVentasFecha(String fecha, String moneda, String ruta, String tipo) {
        GestorVentas venta = new GestorVentas();
        return venta.consultarVentasFecha(fecha, moneda, ruta, tipo);
    }

    /**
     * elimina una venta en el sistema
     *
     * @param fecha
     * @param codigoVenta
     * @return
     */
    public boolean eliminarVenta(String fecha, int codigo, String moneda, String ruta, String tipo) {
        GestorVentas venta = new GestorVentas();

        return venta.eliminar(fecha, codigo, moneda, ruta, tipo);
    }

    /**
     * genera los saldos y los registra en la tabla saldos
     *
     * @param ano
     * @param mes
     * @param moneda
     */
    public boolean generarSaldos(int ano, int mes) {
        GestorVentas venta = new GestorVentas();
        Fecha fecha = new Fecha();
        int limites[] = fecha.retornaLimitesFecha(mes);
        String fechai = fecha.generarFecha(ano, mes, limites[0]);
        String fechaf = fecha.generarFecha(ano, mes, limites[1]);
        ArrayList<VentaDto> lista = venta.consultarVentasXMes(fechai, fechaf);
        return venta.registrarSaldos(lista, ano, mes);

    }

    /**
     * registra el saldo inicial para cada vendedor
     *
     * @param ventadto
     * @param año
     * @param mes
     * @return
     */
    public boolean registrarSaldoInicial(VentaDto ventadto, int año, int mes) {
        GestorVentas venta = new GestorVentas();
        return venta.registrarSaldoInicial(ventadto, año, mes);

    }

    public boolean registrarPrestamo(PrestamoDto pre) {
      
        return GestorPrestamos.getInstance().registrarPrestamo(pre);
    }

    public boolean registrarPago(PrestamoDto pre) {
        
        return GestorPrestamos.getInstance().registrarPago(pre);
    }

    public boolean generarPdfPrestamos(String absolutePath, PersonaDto persona, String moneda,String fechaI,String fechaF) {
        GestorPrestamos gestor = GestorPrestamos.getInstance();
        ArrayList<PrestamoDto> prestamos = gestor.consultarPrestamoEspecifico(persona, moneda,fechaI,fechaF);
        ArrayList<PrestamoDto> pagos = gestor.consultarPagoEspecifico(persona, moneda,fechaI,fechaF);

        PdfInformePrestamos pdf = new PdfInformePrestamos();
        return pdf.generarPDFFactura(prestamos, pagos, persona, moneda,fechaI,fechaF);
    }

}
