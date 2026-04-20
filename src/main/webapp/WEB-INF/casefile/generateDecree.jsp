<%@page import="java.text.SimpleDateFormat"%>
<%@ include file="../content/header2.jsp"%>

<html>
<head>
<meta charset="UTF-8" />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+Devanagari&display=swap"
	rel="stylesheet">

<style>
.btn-sm {
	border-radius: 20px;
	padding: 5px 12px;
	font-size: 12px;
}

.inline-block {
	display: inline-block
}

.pre {
	white-space: pre;
}

.pre-wrap {
	white-space: pre-wrap;
}

.pre-line {
	white-space: pre-line;
}
</style>
</head>
<body>
	<div id="content" class="content">
		<div class="container-fluid" ng-controller="NoticeController"
			oncontextmenu="return false;">

			<input type="hidden" class="form-control" value=${doc_id
				}
				id="doc_id" name="doc_id">

			<div class="row" style="width: 800px">
				<!-- begin col-12 -->
				<!-- begin panel -->

				<div class="panel panel-inverse" data-sortable-id="form-stuff-1">
					<div class="panel-heading">
						<h4 class="panel-title">Generate Decree</h4>
					</div>
					<div class="panel-body">
						<div id="target1">
							<!-- Content will be generated here -->

							<div id="pdfContent"
								style="width: 800px; margin: auto; font-family: 'Times New Roman', serif;">

								<h3 style="text-align: center;"><b>IN THE HIGH COURT OF
								JUDICATURE AT ALLAHABAD</b></h3>

								<div style="margin-top: 25px;">
									<b>CIVIL SIDE</b>
								</div>

								<div style="text-align: center; margin-top: 15px;">
									DECREE OR FORMAL ORDER IN REVISION AND MISCELLANEOUS CASES <br />
									(CHAPTER VII, RULE 8)
								</div>

								<div style="margin-top: 25px; line-height: 1.8;">
									No. <span contenteditable="true"
										style="display: inline-block;  min-width: 120px; padding: 2px 5px; outline: none;"></span>

									of 20 <span contenteditable="true"
										style="display: inline-block;  min-width: 80px; padding: 2px 5px; outline: none;"></span>

									instituted on the <span contenteditable="true"
										style="display: inline-block;  min-width: 80px; padding: 2px 5px; outline: none;"></span>

									day of 20 <span contenteditable="true"
										style="display: inline-block;  min-width: 80px; padding: 2px 5px; outline: none;"></span>
								</div>
								
							<div style="margin-top: 25px; line-height: 1.8;">
									Dated <span contenteditable="true"
										style="display: inline-block;  min-width: 120px; padding: 2px 5px; outline: none;"></span>

									of 20 <span contenteditable="true"
										style="display: inline-block;  min-width: 80px; padding: 2px 5px; outline: none;"></span>

									20  ,  in<span contenteditable="true"
										style="display: inline-block;  min-width: 80px; padding: 2px 5px; outline: none;"></span>

									no. &nbsp;&nbsp;&nbsp;of &nbsp; 20<span contenteditable="true"
										style="display: inline-block;  min-width: 90px; padding: 2px 5px; outline: none;"></span>
								</div>
								
								

								<div style="margin-top: 25px; line-height: 1.8;">
									rising out of the Decree/Order dated <span
										contenteditable="true"
										style="display: inline-block;  min-width: 150px; padding: 2px 5px; outline: none;"></span>
								</div>

								<div style="margin-top: 40px;">
									<div style="float: right;">
										Applicant: <span contenteditable="true"
											style="display: inline-block; min-width: 200px; padding: 2px 5px; outline: none;"></span>
									</div>
									<div ></div>
								</div>

								<div style="text-align: center; margin-top: 60px;">Versus
								</div>

								<div style="margin-top: 40px;">
									<div style="float: right;">
										Opposite Party: <span contenteditable="true"
											style="display: inline-block;  min-width: 200px; padding: 2px 5px; outline: none;"></span>
									</div>
									<div ></div>
								</div>

								<div style="margin-top: 40px; line-height: 1.8;">
									The valuation of the suit for purposes of jurisdiction is Rs. <span
										contenteditable="true"
										style="display: inline-block;  min-width: 150px; padding: 2px 5px; outline: none;"></span>
								</div>

							</div>



						</div>
						<br />

					</div>

				</div>

				<!-- end panel -->

				<!-- end col-12 -->
			</div>
		</div>

		<!-- end row -->
</body>

<!-- ================== END PAGE LEVEL JS ================== -->

<style>
</style>
<!-- <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+Devanagari&display=swap" rel="stylesheet"> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script> -->

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/notice/NoticeController.js?v=7"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/digiSign/signer.js"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/digiSign/conf.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/editor.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/editorjs-text-alignment-block@latest"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/digiSign/conf.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/digiSign/signer.js"></script>

<script>
	$(document).ready(function() {

		$("#txtEditor1").Editor();
		$("#txtEditor2").Editor();
		App.init();

	});

	var ed = document.getElementById('target1');
	var ed1 = document.getElementById('target2');
	var ed2 = document.getElementById('target3');
	var ed3 = document.getElementById('target4');

	var ed11 = document.getElementById('target11');
	var ed12 = document.getElementById('target21');
	var ed23 = document.getElementById('target31');
	var ed34 = document.getElementById('target41');

	/*  ta = document.getElementById('txt'); */

	var key13 = function(ev) {

		if (ev.keyCode == 13) {
			ev.preventDefault();
			const selection = window.getSelection(), range = selection
					.getRangeAt(0), node = document.getSelection().anchorNode, pNode = node.parentNode;
			var tag = pNode.nodeName.toUpperCase();
			if (ev.ctrlKey) {
				tag = prompt('Entet tag name', 'div');
			} else
				switch (tag) {
				case 'P':
					tag = 'BR';
					break;

				case 'DIV':
					tag = 'p';
					break;

				case 'SPAN':
					tag = 'span';
					break;

				case 'BR':
					tag = NULL;
					break;

				default:
					tag = 'BR';

				}

			const el = document.createElement(tag);

			range.deleteContents();
			range.insertNode(el);

			if ('BR' === tag) {
				range.setStartAfter(el);
				range.setEndAfter(el);
			} else {
				range.setStart(el, 0);
				range.setEnd(el, 0);
			}

			const ze = document.createTextNode("\u200B");
			range.insertNode(ze);
			range.setStartBefore(ze);
			range.setEndBefore(ze);

			selection.removeAllRanges();
			selection.addRange(range);
			ev.stopPropagation();
		}

		/* ta.value = ed.innerHTML; */
	}

	ed.addEventListener('keydown', key13, false);
	ed1.addEventListener('keydown', key13, false);
	ed2.addEventListener('keydown', key13, false);
	ed3.addEventListener('keydown', key13, false);
	ed11.addEventListener('keydown', key13, false);
	ed12.addEventListener('keydown', key13, false);
	ed23.addEventListener('keydown', key13, false);
	ed34.addEventListener('keydown', key13, false);

	$(function() {
		var $curParent, Content;
		$(document).delegate("span1", "click", function() {
			if ($(this).closest("s").length) {
				Content = $(this).parent("s").html();
				$curParent = $(this).closest("s");
				$(Content).insertAfter($curParent);
				$(this).closest("s").remove();
			} else {
				$(this).wrapAll("<s />");
			}
		});
	});

</script>
</html>