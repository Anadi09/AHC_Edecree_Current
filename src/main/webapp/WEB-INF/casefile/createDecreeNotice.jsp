<%@ include file="../content/style.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Notice of Decree</title>


<!--=============================================== VIJAY CHAURASIYA ===========================  -->



<!-- PDF Library -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>

<style>

/* RESET */
body {
    margin: 0;
    padding: 0;
    font-family: "Times New Roman", serif;
}

/* A4 PERFECT PAGE */
.page {
    width: 208mm;              /* FIXED (avoid right cut) */
    min-height: 297mm;         
    margin: auto;
    background: #fff;

    padding: 12mm;             /* SAFE PADDING */
    box-sizing: border-box;
}
#pdfContent {
    page-break-after: avoid;
}

table, tr, td {
    page-break-inside: avoid;
}

/* TEXT STYLES */
.center { text-align: center; }

.title {
    font-weight: bold;
    font-size: 20px;
}

.subtitle {
    font-size: 16px;
    margin-top: 8px;
    font-weight: bold;
}

.rule-text {
    font-size: 13px;
}

.divider {
    border-bottom: 2px solid black;
    width: 60%;
    margin: 10px auto;
}

.content-text {
    font-size: 14px;
    margin-top: 15px;
    line-height: 1.6;
    text-align: justify;
}

.right {
    text-align: right;
}

/* TABLE */
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    table-layout: fixed;   /* IMPORTANT */
}

th, td {
    border: 1px solid #000;
    padding: 8px;
    font-size: 13px;
    vertical-align: top;
    word-break: break-word;
}

th {
    background-color: #f0f0f0;
    text-align: center;
}

/* FIX HEIGHT */
td {
    height: 560px;
}

/* FOOTER */
.footer {
  
    bottom: 15mm;
left: 12mm;
margin-top: 10mm;


    display: flex;
    justify-content: space-between; /* LEFT + RIGHT */
    

}

/* PREVENT BREAK */
.page, table, tr, td {
    page-break-inside: avoid !important;
}

/* PRINT */
@media print {
    body { margin: 0; }
    .page { margin: 0; }
}


.toolbar {
    position: fixed;
    top: 10px;
    right: 20px;
    z-index: 999;

    display: flex;
    gap: 10px;
}


.btn-sm {
    border-radius: 20px;
    padding: 5px 12px;
    font-size: 12px;
}



/* Hide in print/PDF */
@media print {
    .toolbar {
        display: none !important;
    }
}

</style>
<script>

// DOWNLOAD PDF
function downloadPDF() {
    const element = document.getElementById('pdfContent');

    const opt = {
        margin: 0,
        filename: 'Notice.pdf',
        image: { type: 'jpeg', quality: 1 },
        html2canvas: { scale: 2, useCORS: true },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
    };

    html2pdf().set(opt).from(element).save();
}

// PRINT
function printPage() {
    window.print();
}

// PREVIEW (open in new tab)
function previewPDF() {
    const content = document.getElementById("pdfContent").innerHTML;

    const win = window.open('', '', 'height=800,width=800');
    win.document.write('<html><head><title>Preview</title>');

    // copy styles
    win.document.write('<style>');
    win.document.write(document.querySelector("style").innerHTML);
    win.document.write('</style>');

    win.document.write('</head><body>');
    win.document.write(content);
    win.document.write('</body></html>');

    win.document.close();
}

</script>

<script>
function downloadPDF() {
    const element = document.getElementById('pdfContent');

    const opt = {
        margin: 0,
        filename: 'Notice.pdf',
        image: { type: 'jpeg', quality: 1 },

        html2canvas: {
            scale: 2,
            useCORS: true,
            scrollX: 0,
            scrollY: 0
        },

        jsPDF: {
            unit: 'mm',
            format: 'a4',
            orientation: 'portrait'
        }
    };

    html2pdf().set(opt).from(element).save();
}
</script>

</head>

<body>

<div class="toolbar">
    <button class="btn-sm btn-primary" onclick="previewPDF()">Preview</button>
    <button class="btn-sm btn-primary" onclick="downloadPDF()">Download</button>
    <button  class="btn-sm btn-primary" onclick="printPage()">Print</button>
</div>



<!-- PDF CONTENT -->
<div class="page mt-3" id="pdfContent" >

    <div class="rule-text">Civil Side</div>

    <div class="center title">
        IN THE HIGH COURT OF JUDICATURE AT ALLAHABAD
    </div>

    <div class="divider"></div>

    <div class="center subtitle">
        NOTICE OF DECREE OR FORMAL ORDER FOR OBJECTION
    </div>

    <div class="divider"></div>

    <div class="center rule-text">(CHAPTER VII, RULE 9)</div>

    <p class="content-text">
        Parties and Advocates are hereby informed that decrees and formal
        orders in the following cases have been drawn up and that they may
        peruse and sign the same or file objections thereto on or before the
        <b>31st day of March 2026</b>.
    </p>

    <div class="right">Deputy Registrar</div>

    <!-- MAIN TABLE -->
    <table>
        <tr>
            <th rowspan="2" style="width:20%;">Description of Case</th>
            <th colspan="2">Name of Parties</th>
            <th colspan="2">Name of Advocates</th>
        </tr>
        <tr>
            <th>Appellant</th>
            <th>Respondent</th>
            <th>Appellant</th>
            <th>Respondent</th>
        </tr>

        <tr>
            <td>
                <c:if test="${not empty caseFileData.caseType}">
                    ${caseFileData.caseType.ct_label}
                </c:if>
                /${caseFileData.fd_case_no}/${caseFileData.fd_case_year}
            </td>

            <td>
                <c:forEach var="p" items="${caseFileData.petitioners}">
                    ${p.pt_name}<br/>
                </c:forEach>
            </td>

            <td>
                <c:forEach var="r" items="${caseFileData.respondents}">
                    ${r.rt_name}<br/>
                </c:forEach>
            </td>

            <td>
                <c:forEach var="c" items="${caseFileData.pCounsels}">
                    ${c.pc_name}<br/>
                </c:forEach>
            </td>

            <td>
                <c:forEach var="c" items="${caseFileData.rCounsels}">
                    ${c.rc_name}<br/>
                </c:forEach>
            </td>
        </tr>
    </table>

    <!-- FOOTER -->
    <div class="footer">
        <div>P.S.U.P-33.H.C.-22.12.2021--20,00(DTP./Offset)</div>
        <div>Decree Section</div>
    </div>

</div>

</body>
</html>

<!--=============================================== VIJAY CHAURASIYA ===========================  -->