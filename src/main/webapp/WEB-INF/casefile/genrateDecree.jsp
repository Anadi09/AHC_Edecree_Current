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
				<div class="panel panel-inverse">
					<div class="panel-heading">
						<div class="panel-heading-btn">
							<a href="javascript:;"
								class="btn btn-xs btn-icon btn-circle btn-default"
								data-click="panel-expand"><i class="fa fa-expand"></i></a>
						</div>
						<h4 class="panel-title">Generate Decree Form</h4>
					</div>
					<div class="panel-body">
						<form class="form-horizontal reduce-gap" name="generateDecree"
							novalidate enctype="multipart/form-data" role="form">
							<div class="modal-body">
								<div ng-if="errorlist" class="alert alert-block alert-danger">
									<ul>
										<span ng-repeat="errors in errorlist "> <span
											ng-repeat="n in errors track by $index">
												<li>{(n)}</li>
									</ul>
								</div>


								<div ng-show="decreeForm!=null">
									<div id="target11" contenteditable="true"
										style="border-style: double; padding-right: 25px; padding-left: 25px; padding-top: 30px; letter-spacing: 1px;"
										contenteditable="true"></div>

									<div
										style="border-style: double; padding-right: 25px; padding-left: 25px; padding-top: 30px;">
										<div id="target21" contenteditable="true"></div>
										<div contenteditable="false">
											<textarea id="txtEditor1" name="abc"
												ng-model="TemplateDescription"
												placeholder="Please Don't Use Special Characters"></textarea>
										</div>
									</div>
									<div contenteditable="true"
										style="border-style: double; padding-right: 25px; padding-left: 25px; padding-top: 30px; letter-spacing: 1px;"
										contenteditable="true">
										<div id="target31"></div>
										</br> </br>
										<div>
											<h2
												style="width: 100%; text-align: center; border-bottom: 1px solid #000; line-height: 0.1em; margin: 10px 0 20px;"></h2>
											<h5>* Here enter the date of judgement or order upon
												which the decree is rounded.</h5>
										</div>
									</div>
									<div contenteditable="true"
										style="border-style: double; padding-right: 25px; padding-left: 25px; padding-top: 30px; letter-spacing: 1px;"
										contenteditable="true">
										<div id="target41"></div>

										<div id="target51">

											<div style="height: 220px">
												<div align="left"
													style="float: left; width: 30%; height: 200px">
													<h6>
														<span>Prepared By <br /> Decree-Writer :
															{{decreeForm.crBy.um_fullname}} <br /> Date
															:{{decreeForm.df_cr_date | date:'dd-MM-yyyy'}} <br /> <br />

															Examined By <br /> Decree-Writer :
															:{{decreeForm.exBy.um_fullname}} <br /> Date
															:{{decreeForm.df_exam_date | date:'dd-MM-yyyy'}}
														</span>

													</h6>

													<h6>
														<span class="danger">*Not signed by the Advocates
															for <br />appellant and respondent <br />though served.
															<br /> Decree-Writer <br /> Date
														</span>
													</h6>

												</div>

												<div align="right"
													style="float: right; width: 30%; height: 220px">

													<h6 align="right" style="color: black; line-height: 8px;">
														<%=role.equals("Deputy Registrar(Decree)") ? user.getUm_fullname() : " "%><br />
														*Deputy Registrar
													</h6>
													<h6 align="right" style="color: black; line-height: 8px;">Allahabad/Lucknow</h6>
													<h6 align="right" style="color: black; line-height: 12px;">
														*(The Deputy Registrar shall give below his<br />
														signature the date on which he actually<br /> signs the
														decree)
													</h6>

													<h6>
														Advocate for appellant <br />Date
														</h5>
														<br /> <br />
														<h6>
															Advocate for respondent <br />Date
															</h5>
												</div>
											</div>




											<div style="width: 100%; padding-top: 10px"">

												<h2
													style="width: 100%; text-align: center; border-bottom: 1px solid #000; line-height: 0.1em; margin: 10px 0 20px;"></h2>
												<h5>* To be scored out when the Advocates have put
													their signatures.</h5>
											</div>
										</div>

									</div>
								</div>
								<div id="pdfContent">
									<div id="target1" ng-show="decreeForm==null"
										style="border-style: double; padding-right: 25px; padding-left: 25px; padding-top: 30px; letter-spacing: 1px;"
										contenteditable="true">
										<div align="center">
											<h2>In The High Court of Judicature at Allahabad</h2>
											<br />
											<h4 align="left">Civil Side</h4>
											<h5>_________________</h5>
											<h4>DECREE IN APPEAL</h4>
											<h5>_________________</h5>
											<h4>(CHAPTER VII,RULE 8)</h4>
											<h5>_________________</h5>
											<h4>Appellate Jurisdiction</h4>
										</div>
										<br />
										<div align="left">
											<h5>{{caseDetailsCIS.caseType}} no.{{caseDet[1]}} of
												{{caseDet[2]}} instituted on the {{da}} day of {{mo}} ,
												{{ye}} from the decree | order of the</h5>
										</div>
										<div align="justify">


											<h5>{{petitioner.petitionerList[0]}}</h5>

											<h5 align="right">Appellant,</h5>
											<h5 align="center">Versus</h5>


											<!-- 	<h5>{{caseDetailsCIS[5]}}</h5> -->

											<h5>
												<span ng-repeat="res in petitioner.respondentList"> <label>{{res}}</label><br />
											</h5>
											<h5 align="right">Respondent.</h5>


											<br /> <br /> <br />
											<h5>
												The valuation of the appeal for purposes of
												<span1> jurisdiction</span1>
												|
												<span1> Court-fees is, Rs.</span1>
											</h5>
										</div>
										<br />
									</div>

									<div ng-show="decreeForm==null"
										style="border-style: double; padding-right: 25px; padding-left: 25px; padding-top: 30px;">
										<div contenteditable="true" id="target2">
											<h5 align="center">(2)</h5>
											<h5>Upon the bearing of this appeal</h5>
											<h5>by the Division Court constituted by</h5>



											<h5 style="float: left">the {{jgName[0]}}</h5>


											<br /> <br />
											<h5 align="center">and</h5>

											<h5>
												{{jgName.length >0 ? jgName[1] : null}} <span
													ng-show="jgName.length==null">the {{jgName[1]}}</span>
											</h5>
											<h5 style="float: right">Judges of this Court</h5>


											<br /> <br /> <br />

											<h5 align="justify">{{advocates.petAdvocate}}</h5>
											<h5 align="right">appearing on behalf of appellant</h5>


											<h5 align="center">and</h5>


											<h5 align="justify">{{advocates.resAdvocate}}</h5>
											<h5 align="right">appearing on behalf of respondent</h5>



											<h5>It is ordered and decreed</h5>
											<h5 style="margin-left: 30px">That</h5>

										</div>

										<div charset="UTF-8" style="page-break-after: always;">
											<textarea id="txtEditor2" name="abc" align="justify"
												ng-paste="cleanPaste($event)" ng-model="TemplateDescription"
												placeholder="Please Don't Use Special Characters"></textarea>

										</div>


									</div>

									<div
										style="border-style: double; padding-right: 25px; padding-left: 35px; padding-top: 30px; page-break-before: always;"
										ng-show="decreeForm==null">
										<div id="target3" contenteditable="true">
											<h5 align="center">(3)</h5>
											<div style="margin-top: 400px;">
												<!-- <p align="center";id="strikethrough" ; > -->
												<!-- <p id="strike"; align="center"; ng-style="{'text-decoration': isStruckThrough ? 'line-through' : 'none'}" ng-click="toggleStrikeThrough()"> -->
												<p align="center"; >
												<h5 style="text-align: justify;">
													&nbsp; &nbsp; &nbsp; &nbsp; And it is further ordered that
													the appellant no./ respondent no. --- aforesaid do pay to
													the respondent no. / appellant no. ---- aforesaid the sum
													of Rs. --- the amount of cost incurred by --- in this
													Court. And it is further ordered that appellant no.
													respondent no. ---- aforesaid do pay to the respondent no.
													/ appellant no.--- aforesaid the sum of Rs. --- the amount
													of costs incurred by -- in the lower court, with interest
													thereon as/awarded/by the said Court
													________________________________ .<br />
													</p>
													<p>&nbsp; &nbsp; &nbsp; &nbsp; Dated this* {{dda}} day
														of {{dmo}} in the year two thousand and {{dye}} .
												</h5>
												</p>
											</div>

										</div>

										<br /> <br />
										<div ng-show="decreeForm==null">
											<h2
												style="width: 100%; text-align: center; border-bottom: 1px solid #000; line-height: 0.1em; margin: 10px 0 20px;"></h2>
											<h5>* Here enter the date of judgement or order upon
												which the decree is rounded.</h5>
										</div>

										<!-- <button  ng-click="toggleStrikeThrough()">Strike All</button> -->

									</div>

									<div ng-show="decreeForm==null"
										style="border-style: double; padding-right: 25px; padding-left: 35px; padding-top: 10px;"
										contenteditable="true">

										<div id="target4">
											<h6 align="center">(4)</h6>
											<br />
											<h6 align="center" style="padding: 10px; line-height: 8px;">MEMORANDUM
												OF COST</h6>
											<br />

											<div>
												<!-- <table border="1"
												style="width: 100%; color: black; font-size: 90%; font-family: 'Courier New', Helvetica, sans-serif;"> -->
												<table border="1"
													style="width: 100%; color: black; font-size: 90%;">
													<tr>
														<th style="width: 60%; border-style: ridge;"><h6
																align="center">
																<b>IN THE DISTRICT</b>
															</h6></th>
														<th colspan="2" style="border-style: ridge;"><h6
																align="center">By Appellant</h6></th>
														<th colspan="2" style="border-style: ridge;"><h6
																align="center">By Respondent</h6></th>
													</tr>

													<tr>
														<td style="border-style: ridge; padding-left: 10px"></td>
														<td style="border-style: ridge;"><h6 align="center">Rs.</h6></td>
														<td style="border-style: ridge;"><h6 align="center">P.</h6></td>
														<td style="border-style: ridge;"><h6 align="center">Rs.</h6></td>
														<td style="border-style: ridge;"><h6 align="center">P.</h6></td>
													</tr>

													<tr>
														<td width="50%" height="20"
															style="border-style: ridge; padding-left: 10px"><b>In
																the Court of first instance</b></td>
														<h4>
															<td style="border-style: ridge;"></td>
															<td style="border-style: ridge;"></td>
															<td style="border-style: ridge;"></td>
															<td style="border-style: ridge;"></td>
															</h6>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>In
																the lower appellant court</b></td>
														<h6>
															<td style="border-style: ridge;"></td>
															<td style="border-style: ridge;"></td>
															<td style="border-style: ridge;"></td>
															<td style="border-style: ridge;"></td>
														</h6>
													</tr>
													<tr>
														<td height="30" style="border-style: none;" colspan="5"><h6
																align="center">
																<b>IN THE HIGH COURT APPEAL</b>
															</h6></td>

													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Stamp
																for memorandum of appeal</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Stamp
																for copies of decrees and judgments</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Stamp
																for vakalatnama</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Process-fee</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Cost
																of summoning records</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Advocate's
																fee</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Fee
																of Advocate's clerks</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Cost
																of paper-book</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Cost
																on remand</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Miscellaneous
																applications with</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Process-fees</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Inspection
																fees</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Other
																costs</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b
															align="center">TOTAL</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>

													<tr>

														<td height="30" style="border-style: none;" colspan="5"><h6
																align="center">
																<b>Cross-Objection</b>
															</h6></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Stamp
																for memorandum of cross-objection</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Stamp
																for vakalatnama</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Advocate's
																fee</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Fee
																of Advocate's clerk</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b>Other
																costs</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
													<tr>
														<td style="border-style: ridge; padding-left: 10px"><b
															align="center">TOTAL</b></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
														<td style="border-style: ridge;"></td>
													</tr>
												</table>
											</div>

											<br />

										</div>

										<div id="target5" ng-show="decreeForm==null">

											<table style="width: 100%; border-collapse: collapse;">
												<tr>

													<!-- LEFT -->
													<td
														style="width: 33%; vertical-align: top; text-align: left;">
														<h6>
															Prepared By <br /> Decree-Writer :
															<%="" + capitalizeWord(user.getUm_fullname())%>
															<br /> Date :
															<%="" + new SimpleDateFormat("dd-MM-yyyy").format(new Date())%>
															<br />
															<br /> Examined By <br /> Decree-Writer :
															<%=role.equals("DECREE EXAMINER") ? user.getUm_fullname() : " "%>
															<br /> Date :
															<%=role.equals("DECREE EXAMINER") ? new SimpleDateFormat("dd-MM-yyyy").format(new Date()) : " "%>
														</h6>

														<h6>
															<span class="danger"> *Not signed by the Advocates
																for <br /> appellant and respondent <br /> though
																served. <br /> Decree-Writer <br /> Date
															</span>
														</h6>
													</td>



													<!-- RIGHT -->
													<td
														style="width: 33%; vertical-align: top; text-align: right;">
														<h6 style="line-height: 8px;">
															<%=role.equals("Deputy Registrar(Decree)") ? user.getUm_fullname() : " "%><br />
															*Deputy Registrar
														</h6>

														<h6 style="line-height: 8px;">Allahabad/Lucknow</h6>

														<h6 style="line-height: 12px;">
															*(The Deputy Registrar shall give <br /> below his
															signature the date on <br /> which he actually signs the
															decree)
														</h6> <br />

														<h6>
															Advocate for appellant <br />Date
														</h6> <br />
													<br />

														<h6>
															Advocate for respondent <br />Date
														</h6>
													</td>

												</tr>
											</table>

											<!-- FOOTER -->
											<div style="width: 100%; padding-top: 10px;">
												<h5 style="text-align: center;">* To be scored out when
													the Advocates have put their signatures.</h5>
											</div>

										</div>
									</div>

									<div align="center">
										<!-- <button class="btn btn-primary" ng-click="digitalSign(decreeForm.df_fd_mid)">Digital Sign</button></td> -->



										<br />
										<button
											ng-hide="decreeForm.df_locked==true  
								 || decreeForm.df_stage_lid==4002 || decreeForm.df_stage_lid==4004  || decreeForm.df_stage_lid==4008
								|| decreeForm.df_stage_lid==4006 || (<%=user.getUm_id()%>!=decreeForm.df_assign_to && decreeForm.df_assign_to!=null)"
											class="btn btn-primary btn-sm" ng-click="saveForm()">save</button>
										<br /> <br />

										<%-- <br/><button ng-hide="decreeForm.df_locked==true   
								|| decreeForm.df_stage_lid==4002 || decreeForm.df_stage_lid==4004  || decreeForm.df_stage_lid==4008
								|| decreeForm.df_stage_lid==4006 || (<%= user.getUm_id() %>!=decreeForm.df_assign_to && decreeForm.df_assign_to!=null)"  
								class="btn btn-primary btn-sm" ng-click="updateForm(decreeForm)">Update</button>
								<br/> <br/> --%>

										<br />
										<%
										if (user.getUserroles().get(0).getLk().getLk_longname().equals("Deputy Registrar(Decree)")) {
										%>
										<br />
										<button
											ng-show="decreeForm.df_locked==false   
								&& decreeForm.df_stage_lid==4008 "
											class="btn btn-primary btn-sm" ng-click="saveForm()">Approve</button>
										<%
										}
										%>
										<br /> <br /> <span
											ng-repeat="data in decreeForm.decreeFileUploaded"
											ng-hide="decreeForm.df_locked==true"> <a href=""
											ng-click="preview(data.dfu_id)">{{data.dfu_file_name}}</a>
											<button class="btn btn-primary btn-sm"
												ng-click="deleteFile(data.dfu_id)">Delete</button> <br />

										</span> <br />

										<%
										if (user.getUserroles().get(0).getLk().getLk_longname().equals("DECREE CREATOR")) {
										%>

										<div
											ng-show="decreeForm.df_stage_lid==4000 && decreeForm.df_file_name==null&& !hideUploadBtn">
											<label for="file">File<span class="star">*</span></label> <input
												type="file" style="color: red;" ngf-select
												ng-model="picFile" name="file">
											<button class="btn btn-primary btn-sm"
												ng-click="uploadFile()">Upload</button>
										</div>
										<br />

						

										<select class="form-control" ng-model="assign_to"
											style="width: 250px"
											<%-- ng-show="decreeForm.df_stage_lid==4000 || decreeForm.df_assign_to == <%=user.getUm_id()%>" --%>
										ng-show="(decreeForm.df_stage_lid==4000 || decreeForm.df_stage_lid==4002 || decreeForm.df_stage_lid==4004 || decreeForm.df_stage_lid==4006) 
										&& decreeForm.df_assign_to == <%=user.getUm_id()%>"
											ng-options="um.um_id as um.um_fullname for um in decreeCreator">

											<option value="">Select Decree Examinor/Deputy
												Registrar</option>
										</select></br>

										<button
											ng-show="(decreeForm.df_stage_lid==4000 || decreeForm.df_stage_lid==4002 || decreeForm.df_stage_lid==4004 || decreeForm.df_stage_lid==4006) 
										&& decreeForm.df_assign_to == <%=user.getUm_id()%>"
											class="btn btn-primary btn-sm" ng-click="nextStage(4001)">
											Assign To</button>
										</br>

										<button
											ng-show="(decreeForm.df_stage_lid==4002 || decreeForm.df_stage_lid==4004 || decreeForm.df_stage_lid==4006) 
										&& decreeForm.df_assign_to == <%=user.getUm_id()%>"
											class="btn btn-primary btn-sm" data-toggle="modal"
											data-target="#returntodecree">Return Remark</button>


										<%
										}
										%>

										<%
										if (user.getUserroles().get(0).getLk().getLk_longname().equals("Deputy Registrar(Decree)")) {
										%>


										<button
											ng-show="decreeForm.df_stage_lid==4008 && decreeForm.df_locked==false"
											class="btn btn-primary" data-toggle="modal"
											data-target="#returntodecree">Return Remark</button>

										<%
										}
										%>


										<div class="panel panel-inverse overflow-hidden"
											ng-hide="decreeForm.df_stage_lid==4000 ">



											<div class="modal fade" id="returntodecree" tabindex="-1"
												role="dialog" aria-labelledby="myModalLabel"
												aria-hidden="true">
												<div class="modal-dialog modal-lg" style="height: 200px;">
													<div class="modal-content">
														<div class="modal-header">
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
															<h4 class="modal-title" id="myModalLabel">
																<strong>Return to Decree Writer</strong>
															</h4>
														</div>
														<div class="modal-body">

															<div class="panel-body">
																<div class="form-group pull-in clearfix">
																	<div class="col-sm-3">
																		<div>
																			<label class=" control-label" for="file">Remarks<span
																				class="star">*</span></label>
																			<textarea type="text" class="form-control"
																				ng-model="decreeForm.remark" name="file"
																				style="width: 200%; height: 100px"></textarea>
																			<!--  <textarea class="form-control" ng-model="registerCase.remark" style="left: 50%;" ></textarea> -->

																		</div>
																	</div>
																</div>

																<!-- <button id="btnPrint" class="btn btn-primary">Print</button> -->
																<input type="submit" value="Submit" id="Submit"
																	ng-click="returnToDecreeWriter(decreeForm)"
																	ng-disabled="buttonDisabled"
																	data-loading-text="Loading..." class="btn btn-primary"
																	data-toggle="modal" />
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>




									<%
									if (user.getUserroles().get(0).getLk().getLk_longname().equals("Deputy Registrar(Decree)")) {
									%>
									<div
										ng-show="decreeForm.df_stage_lid==4008 && decreeForm.df_locked==true">
										<button
											ng-show="decreeForm.df_stage_lid==4000 || decreeForm.df_stage_lid==4002 || decreeForm.df_stage_lid==4004 
								|| decreeForm.df_stage_lid==4006"
											ng-show="decreeForm.df_id" class="btn btn-primary btn-sm"
											ng-click="nextStage(4001)">Assign To</button>
										<br />
										<button
											ng-show="decreeForm.df_stage_lid==4002 || decreeForm.df_stage_lid==4004 
								|| decreeForm.df_stage_lid==4006"
											class="btn btn-primary" data-toggle="modal"
											data-target="#returntodecree">Return Remark</button>
									</div>

									<div
										ng-hide="decreeForm.df_stage_lid==4008 && decreeForm.df_locked==true">
										<label class="col-md-4 control-label" for="file">File<span
											class="star">*</span></label> <input type="file" ngf-select
											ng-model="picFile" name="file">
										<button class="btn btn-primary btn-sm" ng-click="uploadFile()">Upload</button>
										<button class="btn btn-primary btn-sm"
											ng-click="downloadDecree()">download</button>
									</div>

									<!-- <td><button class="btn btn-primary" ng-click="digitalSign(decreeForm.df_fd_mid)">Digital Sign</button></td> -->
									<%
									}
									%>

									<!-- <button ng-show="decreeForm.df_locked==true" class="btn btn-primary btn-sm" ng-click="downloadDecree()">download</button> -->
									<button
										ng-show="decreeForm.df_locked==true && decreeForm.df_stage_lid==4008"
										class="btn btn-primary btn-sm" ng-click="downloadDecree()">download</button>

									<span ng-show="decreeForm.df_locked==true"><b>
											Decree already generated. </b></span>
									<!-- <button ng-show="decreeForm.df_stage_lid!==null" class="btn btn-primary btn-sm" ng-click="downloadDecree()">Preview</button> -->

									<button ng-show="decreeForm.df_stage_lid !==4008  "
										class="btn btn-primary btn-sm" ng-click="generateDoc()">Download
										Doc File only for Hindi</button>

									<button
										ng-show="decreeForm.df_stage_lid !==4008 && decreeForm.df_stage_lid !=4000"
										class="btn btn-primary btn-sm" ng-click="downloadDecree()">Download
										PDF File only for English</button>

	


								</div>
							</div>
					</div>
					</form>




				</div>
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

/* #strikethrough {
  text-decoration: line-through;
}  */
.crossed {
	background: linear-gradient(to top left, rgba(0, 0, 0, 0) 0%,
		rgba(0, 0, 0, 0) calc(50% - 0.8px), rgba(0, 0, 0, 1) 50%,
		rgba(0, 0, 0, 0) calc(50% + 0.8px), rgba(0, 0, 0, 0) 100%),
		linear-gradient(to top right, rgba(0, 0, 0, 0) 0%, rgba(0, 0, 0, 0)
		calc(50% - 0.8px), rgba(0, 0, 0, 1) 50%, rgba(0, 0, 0, 0)
		calc(50% + 0.8px), rgba(0, 0, 0, 0) 100%);
}
</style>


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

	/* const editor = new EditorJS({
		  holder: 'editorjs',
		  async onChange() {
		    const content = await editor.save();
		    document.getElementById("txtEditor2").value = JSON.stringify(content);
		  }
		}); */
</script>
</html>