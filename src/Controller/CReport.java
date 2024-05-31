/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DReport;
import Koneksi.Koneksi;
import Model.MReport;
import View.VReport;
import java.sql.SQLException;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MSI-PC
 */
public class CReport {

    Koneksi k;
    VReport view;
    DReport dao;

    public CReport(VReport view) {
        this.view = view;
    }

    public void report() throws ClassNotFoundException, JRException {
        k = new Koneksi();
        dao = new DReport();
        List<MReport> list = null;
        String jaspertName = null;
        String fileName = null;
        try {
            if (view.getCboJenisLaporan().getSelectedItem().equals("Pengadaan Barang")) {
                list = dao.queryPengadaanBarangHarian(k.getKoneksi(), view.getCboBulan().getSelectedItem().toString(), view.getCboTahun().getSelectedItem().toString());
                fileName = "ReportPengadaanBarangHarian_";
                jaspertName = "PengadaanBarangHarian_A4.jrxml";
            } else if (view.getCboJenisLaporan().getSelectedItem().equals("Transaksi")) {
                list = dao.queryTransaksiHarian(k.getKoneksi(), view.getCboBulan().getSelectedItem().toString(), view.getCboTahun().getSelectedItem().toString());
                fileName = "ReportTransaksiHarian_";
                jaspertName = "TransaksiHarian_A4.jrxml";
            }

            /*Convert List to JRBeanCollectionDataSource */
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);

            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("CollectionBeanParam", itemsJRBean);

            //read jrxml file and creating jasperdesign object
            InputStream input;

            try {
                File file = new File(".");
                input = new FileInputStream(file.getCanonicalPath() + "/src/Reports/" + jaspertName);
                JasperDesign jasperDesign = JRXmlLoader.load(input);

                /*compiling jrxml with help of JasperReport class*/
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                /* Using jasperReport object to generate PDF */
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

                JOptionPane.showMessageDialog(view, "Cetak Berhasil");

                /*call jasper engine to display report in jasperviewer window*/
                JasperViewer.viewReport(jasperPrint);

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd-HHmmss");
                    Date date = new Date();
                    String outputFile = "D:\\DOWNLOADS\\FOLDER DESKTOP\\iPOS_99\\src\\Reports\\" + fileName + formatter.format(date) + ".pdf";

                    /* outputStream to create PDF */
                    OutputStream outputStream = new FileOutputStream(new File(outputFile));

                    /* Write content to PDF file */
                    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage());
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(CReport.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }
}
