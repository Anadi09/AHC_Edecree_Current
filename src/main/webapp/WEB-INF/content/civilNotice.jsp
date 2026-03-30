<%@ include file="../content/header2.jsp"%>
  <html lang="en">
  <head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
   <!--  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="civil.css"> -->
    <title>civil</title>
  </head>
  <body>
  <div id="content" class="content" style="background-color: white;">
            <div class="container-fluid" ng-controller="NoticeController" oncontextmenu="return false;">
            <input type="hidden" class="form-control" value=${doc_id} id="doc_id" name="doc_id">
<div id="target" style="background:white;" >
 <div id="pdfPrep" style="margin-left: 20%;margin-right: 40%;width: 75%;letter-spacing: 2px;">
 <div id="target1">
         <div  align="center">
          <h2><b>IN THE HIGH COURT OF JUDICATURE AT ALLAHABAD</b><h2/>
          <b> QUASI CRIMINAL SIDE</b>
            <h4>No. 48 - Notice</h4>
          <h3>[CHAPTER XXXV-E, Rule 6]</h3>
          </div>
          <p style="width: 700px;font-size: 1.2em;letter-spacing: 3px;color:black;"><br/>
         In the matter of Miscellaneous {{caseDetailsCIS[1]}} Case No.  <b>{{caseDetailsCIS[2]}} of {{caseDetailsCIS[3]}}</b> </p><br/>
         
       <h5 align="center">Between</h5>
       
       <!--  <p align="center" > -->
        <div class="row" style="color:black;width: 700px;font-size: 1.2em;letter-spacing: 3px;">
  <div class="column"><label for="{{caseDetailsCIS[4]}}">{{caseDetailsCIS[4]}}</label><br/>
  <p ng-repeat="pet in petitioner"><label for="{{pet[1]}}">{{pet[1]}}</label><br/></p></div>
  <div class="column" style="text-align: center;vertical-align: middle;">Applicant,</div>
</div>
             <!-- </p><br/> -->
      
       <h5 align="center">AND</h5>
       
        <div class="row" style="color:black;width: 700px;font-size: 1.2em;letter-spacing: 3px;">
  <div class="column" id="div1">
 <!--  {{caseDetailsCIS[5]}}
  <p  ng-repeat="res in respondent">{{res[1]}}</p></p> -->
 <input type="checkbox" id="{{caseDetailsCIS[5]}}" data-html2canvas-ignore="true" ng-model="selectedList[caseDetailsCIS[5]]"/>
    <label for="{{caseDetailsCIS[5]}}">{{caseDetailsCIS[5]}}</label><br/>
  
  <span ng-repeat="res in respondent">
    <input type="checkbox" id="{{res[1]}}" data-html2canvas-ignore="true" ng-model="selectedList[res[1]]"/>
    <label for="{{res[1]}}">{{res[1]}}</label><br/>
</span>
  
  </div>
  <div class="column" id="div2" >Opposite-party,</div>
</div><br/>
       <!--  <p align="center" >
      {{caseDetailsCIS[5]}}   </p><br/> -->
         <div style="width: 700px;font-size: 1.2em;letter-spacing: 3px;color:black">
          <p align="left" >To, </p><br/>
           <p align="center" > <span style="background-color: #ff6;" contenteditable="true">{{recipient}} </span>  </p><br/>
          <p >WHEREAS the above named applicant has represented to this Court that you have committed Contempt of Court </p><br/>
         <p >AND WHEREAS the .... day of .... 20.. has been fixed for the hearing of the said case. </p><br/>
          <p >AND WHEREAS the .... day of .... 20.. has been fixed for the hearing of the said case. </p><br/>
          
           <p  >Notice is hereby given to calling upon you to appear in person/through an Advocate in this Court
           on the above mentioned date at 10 O'clock in the forenoon to show cause why you shold not be  punished for Contempt of Court.<br/>
           Given under my hand and the seal of the Court, This.... day of ....2022 </p><br/>
           
           <br/><br/><p align="right" >Joint Registrar </p><br/><br/>
           
           <p align="left" >Note - Copy of Court's Order* Allahabad<br/>
           Dated....<br/> 
           Note - A copy of the application and the affidavit of the report , as the case may be shall accompany in the notice. </p><br/>
           
         </div> </div> 
            <div id="target2" style="width: 700px;font-size: 1.2em;letter-spacing: 3px;color:black;">
            <p align="left" >To, </p><br/>
            <h3><center><span style="background-color: #ff6;" contenteditable="true">THE DISTRICT MAGISTRATE /C.J.M</span></center></h3><br/> <br/>
            <p align="left" >SIR, </p><br/>
             <p align="left" > I am directed to enclose herewith <span style="background-color: #ff6;" contenteditable="true">one</span>
              notice in duplicate alongwith <span style="background-color: #ff6;" contenteditable="true">one</span> copies/copy 
              of <br/>-----------------------------------------<br/>
                     <b>{{caseDetailsCIS[1]}} Case No.  {{caseDetailsCIS[2]}} of {{caseDetailsCIS[3]}}<br/>{{caseDetailsCIS[4]}} <br/> Versus <br/>
                      {{caseDetailsCIS[5]}}<br/></b>-----------------------------------------<br/>
              
             for service on the person named therein and ask you kindly to return the original notices duly endorsed after service to this Court before .... 
             the date now fixed for the hearing of the case noted on the margin</p><br/>
             
             ENCLOSURES :<br/>
               &ensp;1.Notice in duplicate <br/>
               &ensp;2.Copy <br/>
              &ensp;2.Copy <br/>
               
               <br/> <div align="right"><b>Assistant Registrar</b></div>
             </div>
         
         </div>
         </div>
         <button ng-click="test5()">Click</button></div>
         </div>
         <!-- <button ng-click="testMethod()">Click</button></div> -->
 </body>

<style>
/* Create two equal columns that floats next to each other */
.column {
  float: left;
  width: 50%;
  padding: 10px;
  height: auto; /* Should be removed. Only for demonstration */
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
</style>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/notice/NoticeController.js?v=2"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootbox.min.js"></script>

      <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts/controllers/editor.js"></script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
<script src="https://rawgit.com/eKoopmans/html2pdf/master/dist/html2pdf.bundle.min.js"></script>
    
     
      <script>
      document.getElementById("div2").style.height = (document.getElementById("div1").clientHeight - 10) + "px";
      window.onload = function() {
    	  var spans = document.getElementsByTagName("span"),
    	      index,
    	      span;
    	  
    	  for (index = 0; index < spans.length; ++index) {
    	    span = spans[index];
    	    if (span.contentEditable) {
    	      span.onblur = function() {
    	        var text = this.innerHTML;
    	        text = text.replace(/&/g, "&amp").replace(/</g, "&lt;");
    	        /* display("Content committed, span " +
    	                (this.id || "anonymous") +
    	                ": '" +
    	                text + "'"); */
    	      };
    	    }
    	  }
      }
    	  
    	  function onClick() {
    		  let doc = new jsPDF('l', 'in', 'a4');

    		  doc.internal.scaleFactor = 30;
    		  doc.addHTML(document.getElementById('target'),function() {
    		      doc.save('html.pdf');
    		  });
    	  };

    		 /*  var element = document.getElementById("clickbind");
    		  element.addEventListener("click", onClick); */
    	  
    	 
      
        $(document).ready(function() {
          
            App.init();

        });
    </script>
 
</html>