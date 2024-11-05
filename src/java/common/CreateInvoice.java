/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import dto.InvoiceHeadDetails;
import dto.ItemInfoReport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.OrderLine;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author ASUS
 */
public class CreateInvoice {

    public String crate(String invoiceId, ArrayList<OrderLine> orderLineList, InvoiceHeadDetails iht) {
        String savingPath = null;
        try {
            String reportPath = new Paths().getCustomerInvoiceJRXMLPath();
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            List<ItemInfoReport> itemList = new ArrayList<ItemInfoReport>();
            for (OrderLine orderLine : orderLineList) {
                String itemName = orderLine.getProductItem().getName();
                String qty = String.valueOf(orderLine.getQty());
                String discount = "none";
                if(orderLine.getPromotion() != null){
                    discount = orderLine.getPromotion().getName();
                }
                String price = String.valueOf(orderLine.getPrice());
                itemList.add(new ItemInfoReport(itemName, qty, discount, price));
            }      

            // Create a data source from the list
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(itemList);

            // Parameters for the report (if any)
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("companyAddress", iht.getCompanyAddress());
            parameters.put("companyEmail", iht.getCompanyEmail());
            parameters.put("companyCnt", iht.getCompanyCnt());
            parameters.put("currentDate", iht.getCurrentDate());
            parameters.put("invoiceNumber", iht.getInvoiceNumber());
            parameters.put("customerName", iht.getCustomerName());
            parameters.put("email", iht.getEmail());
            parameters.put("shippingAddress", iht.getShippingAddress());
            parameters.put("orderedDate", iht.getOrderedDate());
            parameters.put("itemTotal", iht.getItemTotal());
            parameters.put("discountAmount", iht.getDiscountAmount());
            parameters.put("shippingCost", iht.getShippingCost());
            parameters.put("totalPayment", iht.getTotalPayment());

            // Fill the report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export the report to a PDF file
            savingPath = new Paths().getCustomerInvoiceSavePath() + invoiceId + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, savingPath);

            // Export the report to an HTML file
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, "EmployeeReport.html");
            System.out.println("Report generated successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }
        return savingPath;
    }
}
