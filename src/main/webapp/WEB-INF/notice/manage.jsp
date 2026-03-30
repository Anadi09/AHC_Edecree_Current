<%@ include file="../content/header2.jsp"%>
<html>

<body>
	<div id="content" class="content">
		<div class="container-fluid" ng-controller="NoticeController"
			oncontextmenu="return false;">

			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Notice Form</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form>
								<h2 class="heading">In the High Court of Judicature at
									Allahabad</h2>

								<div class="row">
									<div class=" col-md-4 col-md-offset-2">

										<h5 class="heading2">&nbsp; &nbsp; &nbsp; Civil SIDE</h5>
									</div>
									<div class=" col-md-5 col-md-offset-1">

										<h5 class="heading2">&nbsp; &nbsp; &nbsp;( Chapter XII,
											Rules 1 and 7)</h5>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3">


										<span class="inlineEle">Miscellaneous application - No.</span>
									</div>

									<div class="col-md-4">

										<input type="text" class="form-control" id="inputCity"
											ng-model="applicationNotice.a1">
									</div>



									<span class="inlineEle col-md-1">of</span>
									<div class="col-md-2">
										<select id="inputState" class="form-control "
											ng-model="applicationNotice.a2">
											<option>Year</option>
											<option>2018</option>
											<option>2019</option>
											<option>2020</option>
										</select>
									</div>
								</div>
						</div>
						<div class="row">

							<div class="col-md-3">
								<span class="inlineEle col-md-2 col-md-offset-8"
									style="text-align: right;">No.</span>
							</div>
							<div class="col-md-4">

								<input type="text" class="form-control" id="inputCity"
									ng-model="applicationNotice.a3">
							</div>

							<span class="inlineEle col-md-1">of</span>

							<div class="col-md-2 ">
								<select id="inputState" class="form-control "
									ng-model="applicationNotice.a4">
									<option selected>Year</option>
									<option>2018</option>
									<option>2019</option>
									<option>2020</option>
								</select>
							</div>



						</div>

						<div class="row">
							<div class="form-group col-md-4 col-md-offset-4">

								<input type="text" class="form-control" id="inputCity"
									ng-model="applicationNotice.a5">
							</div>


						</div>
						<div class="form-row">
							<div class="form-group col-md-2 col-md-offset-6 itemAlign">
								Versus</div>


						</div>
						<div class="form-row">
							<div class="form-group col-md-4 col-md-offset-4">

								<input type="text" class="form-control" id="inputCity"
									ng-model="applicationNotice.a6">
							</div>


						</div>

						<div class="row">
							<div class="form-group col-md-4 col-md-offset-1">
								<div class="md-form">
									<span><span>To,</span> <i
										class="fas fa-pencil-alt prefix col-md-5"></i> <textarea
											id="form10" class="md-textarea form-control col-md-offset-8"
											rows="5" ng-model="applicationNotice.a7"></textarea>
								</div>
							</div>
						</div>

						<div class="row">

							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									&nbsp; &nbsp; &nbsp; &nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Whereas the above mentioned
									applicant has made an application to the court for <span>
										<input type="text" id="inputCity"
										ng-model="applicationNotice.a8">
									</span> in the above noted case you are hereby called upon to enter
									appearance on or before the <span> <input type="text"
										id="inputCity" ng-model="applicationNotice.a9"></span> day of
									<span> <input type="text" id="inputCity"
										ng-model="applicationNotice.a10"></span> to show cause why
									the application be not granted. The said application will be
									heard on such day thereafter as may be subsequently notified in
									accordance with the rules
								</h6>
							</div>

							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">&nbsp;
									&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Take
									Notice that in default of appearance on or before the day
									before mentioned in person are by advocate or by some person by
									law authorised to act on your behalf the application will be
									heard and determined in your absence.</h6>
							</div>
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									&nbsp; &nbsp; &nbsp; &nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; A copy of the application
									together with a copy of the affidavit filed by the application
									is annexed here to.<span> <input type="text"
										id="inputCity" ng-model="applicationNotice.a11"></span> day
									of <span> <input type="text" id="inputCity"
										ng-model="applicationNotice.a12"></span>
								</h6>
							</div>

							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Given under my hand and the seal of court this day of <input
										type="text" id="inputCity" ng-model="applicationNotice.a13">
									<input type="text" id="inputCity"
										ng-model="applicationNotice.a14">
								</h6>
							</div>
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Advocate For : <input type="text" id="inputCity"
										ng-model="applicationNotice.a15">
								</h6>
							</div>

						</div>
						<div class="row">
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Date - <span> <input type="text"
										ng-model="applicationNotice.a16"></span>

								</h6>
							</div>

						</div>
						<div class="row">
							<div class="form-group col-md-4 col-md-offset-8 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Deputy Registrar <br> Allahabad/Lucknow

								</h6>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Note- A process fee of Rs <span> <input type="text"
										ng-model="applicationNotice.a17"></span> Chargeable under

								</h6>
							</div>

						</div>
						<div class="row">
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Date - <span> <input type="text"
										ng-model="applicationNotice.a18"></span>

								</h6>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Chapter XXXVII, Rule 2 of Rules of Court, author has been paid.
								</h6>
							</div>
						</div>
						<!-- <div class="row">

						<button type="submit" class="btn btn-primary" style ="padding-right: 15px; padding-left: 15px;"
							ng-click="submitForm()">Preview Notice</button>
							</div> -->
						<!-- <div class="row">
							<div class="modal-footer"
								style="margin-left: 10px; margin-right: 10px;">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary"
									ng-click="submitForm()">Preview Notice</button>
							</div>
						</div> -->

						<div class="row">
							<div class="modal-footer"
								style="margin-left: 10px; margin-right: 10px;">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#exampleModal1">Preview
									Notice</button>
							</div>
						</div>
						</form>

					</div>


				</div>
			</div>


			<div class="modal fade" id="exampleModal1" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel1"
				aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Notice Form</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form>
								<h2 class="heading">In the High Court of Judicature at
									Allahabad</h2>

								<div class="row">
									<div class=" col-md-4 col-md-offset-2">

										<h5 class="heading2">&nbsp; &nbsp; &nbsp; Civil SIDE</h5>
									</div>
									<div class=" col-md-5 col-md-offset-1">

										<h5 class="heading2">&nbsp; &nbsp; &nbsp;( Chapter XII,
											Rules 1 and 7)</h5>
									</div>
								</div>

								<div class="row">
									<div class="col-md-3">
										<span class="inlineEle">Miscellaneous application - No.</span>
									</div>

									<div class="col-md-4 ">
										<span ng-if="applicationNotice.a1">
											{{applicationNotice.a1}} </span> <span ng-if="!applicationNotice.a1">
											__________________ </span>
									</div>




									<span class="inlineEle col-md-1">of</span>
									<div class="form-group  col-md-3 ">
										<span ng-if="applicationNotice.a2">
											{{applicationNotice.a2}} </span> <span ng-if="!applicationNotice.a2">
											__________________ </span>
									</div>
								</div>




								<div class="row">
									<div class="col-md-3">
										<span class="inlineEle col-md-2 col-md-offset-8"
											style="text-align: right;">No.</span>
									</div>
									<div class="col-md-4">

										<span ng-if="applicationNotice.a3">
											{{applicationNotice.a3}} </span> <span ng-if="!applicationNotice.a3">
											__________________ </span>
									</div>

									<span class="inlineEle col-md-1">of</span>

									<div class="form-group  col-md-3 ">
										<span ng-if="applicationNotice.a4">
											{{applicationNotice.a4}} </span> <span ng-if="!applicationNotice.a4">
											__________________ </span>
									</div>
								</div>
						</div>

						<div class="row">
							<div class="form-group col-md-4 col-md-offset-5">

								<span ng-if="applicationNotice.a5">
									{{applicationNotice.a5}} </span> <span ng-if="!applicationNotice.a5">
									__________________ </span>
							</div>
						</div>

						<div class="row">
							<div id="ver" class="form-group col-md-4 col-md-offset-4">
								Versus</div>
						</div>

						<div class="row">
							<div class="form-group col-md-4 col-md-offset-5">

								<span ng-if="applicationNotice.a6">
									{{applicationNotice.a6}} </span> <span ng-if="!applicationNotice.a6">
									__________________ </span>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-4 col-md-offset-1"
								ng-if="applicationNotice.a7">
								<div class="md-form">
									<span>To,</span> <i class="fas fa-pencil-alt prefix col-md-5"></i>
									<textarea style="border: none" ng-if="applicationNotice.a7"
										id="form10" class="md-textarea form-control col-md-offset-8"
										rows="5" ng-model="applicationNotice.a7" readonly></textarea>
									<!-- <textarea ng-if="!applicationNotice.a7" id="form11sss" class="md-textarea form-control col-md-offset-8" rows="8" readonly
											 <span ng-if="!applicationNotice.a7">
									__________________ </span>
								
											</textarea> -->
								</div>


							</div>
							<div class="form-group col-md-4 col-md-offset-1"
								ng-if="!applicationNotice.a7">
								<div class="md-form">
									<span>To,</span> <i class="fas fa-pencil-alt prefix col-md-5"></i>


									<span ng-if="!applicationNotice.a7"> __________________
									</span>


								</div>


							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									&nbsp; &nbsp; &nbsp; &nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Whereas the above mentioned
									applicant has made an application to the court for <span
										ng-if="applicationNotice.a8"> {{applicationNotice.a8}}
									</span> <span ng-if="!applicationNotice.a8"> __________________
									</span> in the above noted case you are hereby called upon to enter
									appearance on or before the <span ng-if="applicationNotice.a9">
										{{applicationNotice.a9}} </span> <span ng-if="!applicationNotice.a9">
										__________________ </span> day of <span> <span
										ng-if="applicationNotice.a10">
											{{applicationNotice.a10}} </span> <span
										ng-if="!applicationNotice.a10"> __________________ </span>
									</span>to show cause why the application be not granted. The said
									application will be heard on such day thereafter as may be
									subsequently notified in accordance with the rules
								</h6>
							</div>

							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">&nbsp;
									&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Take
									Notice that in default of appearance on or before the day
									before mentioned in person are by advocate or by some person by
									law authorised to act on your behalf the application will be
									heard and determined in your absence.</h6>
							</div>
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									&nbsp; &nbsp; &nbsp; &nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; A copy of the application
									together with a copy of the affidavit filed by the application
									is annexed here to..<span ng-if="applicationNotice.a11">
										{{applicationNotice.a11}} </span> <span
										ng-if="!applicationNotice.a11"> __________________ </span> day
									of <span ng-if="applicationNotice.a12">
										{{applicationNotice.a12}} </span> <span
										ng-if="!applicationNotice.a12"> __________________ </span>
								</h6>
							</div>

							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Given under my hand and the seal of court this day of <span
										ng-if="applicationNotice.a13">
										{{applicationNotice.a13}} </span> <span
										ng-if="!applicationNotice.a13"> __________________ </span> <span
										ng-if="applicationNotice.a14">
										{{applicationNotice.a14}} </span> <span
										ng-if="!applicationNotice.a14"> __________________ </span>
								</h6>
							</div>
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Advocate For : <span ng-if="applicationNotice.a15">
										{{applicationNotice.a15}} </span> <span
										ng-if="!applicationNotice.a15"> __________________ </span>
								</h6>
							</div>

						</div>
						<div class="row">
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Date - <span> <span ng-if="applicationNotice.a16">
											{{applicationNotice.a16}} </span> <span
										ng-if="!applicationNotice.a16"> __________________ </span></span>

								</h6>
							</div>

						</div>
						<div class="row">
							<div class="form-group col-md-4 col-md-offset-8 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Deputy Registrar <br> Allahabad/Lucknow

								</h6>
							</div>


						</div>
						<div class="row">
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Note- A process fee of Rs <span ng-if="applicationNotice.a17">
										{{applicationNotice.a17}} </span> <span
										ng-if="!applicationNotice.a17"> __________________ </span>
									Chargeable under

								</h6>
							</div>

						</div>
						<div class="row">
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Date - <span ng-if="applicationNotice.a18">
										{{applicationNotice.a18}} </span> <span
										ng-if="!applicationNotice.a18"> __________________ </span>

								</h6>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12 ">
								<h6 style="padding-right: 15px; padding-left: 15px;">
									Chapter XXXVII, Rule 2 of Rules of Court, author has been paid.
								</h6>
							</div>
						</div>
						<!-- <div class="row">

						<button type="submit" class="btn btn-primary" style ="padding-right: 15px; padding-left: 15px;"
							ng-click="submitForm()">Submit Notice</button>
							</div> -->
						<div class="row">
							<div class="modal-footer"
								style="margin-left: 10px; margin-right: 10px;">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary"
									ng-click="submitForm1()">Submit Notice</button>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>


			<div class="modal fade" id="exampleCaseModal" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Notice Form</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form>
								<h2 class="heading">In the High Court of Judicature at
									Allahabad</h2>
								<h5 class="heading">NOTICE OF REVISION</h5>
								<h6 class="heading">(Chapter XII, Rules 1 and 7)</h6>
								<h6 class="heading1">Revisional Jurisdiction</h6>

								<div class="row">
									<div class="form-group  col-md-2 col-md-offset-2 ">
										<select id="inputState" class="form-control "
											ng-model="caseNotice.a1">
											<option selected>Type</option>
											<option>2020</option>
											<option>2019</option>

										</select>
									</div>


									<div class="col-md-1 col-md-offset-0">

										<span class="inlineEle">No.</span>
									</div>


									<div class="col-md-3 ">

										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a2">
									</div>
									<div class="col-md-1">

										<span class="inlineEle">of</span>
									</div>

									<div class="form-group  col-md-2 ">
										<select id="inputState" class="form-control"
											ng-model="caseNotice.a3">
											<option selected>Year</option>
											<option>2020</option>
											<option>2019</option>

										</select>
									</div>
								</div>

								<div class="row">
									<div class="form-group col-md-4 col-md-offset-4">

										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a4">
									</div>


								</div>
								<div class="row">
									<div class="form-group col-md-4 col-md-offset-6 itemAlign">
										Versus</div>
								</div>

								<div class="row">
									<div class="form-group col-md-4 col-md-offset-4">

										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a5">
									</div>

								</div>
								<br> <br> <br>


								<div class="row">

									<div class="col-md-2">

										<span class="inlineEle">Arision our of</span>
									</div>


									<div class=" col-md-3">

										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a6">
									</div>

									<div class="col-md-1">

										<span class="inlineEle">on</span>
									</div>

									<div class="col-md-3">

										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a7">
									</div>

									<div class="col-md-1">

										<span class="inlineEle">of</span>
									</div>

									<div class="form-group  col-md-2">
										<select id="inputState" class="form-control "
											ng-model="caseNotice.a8">
											<option selected>Year</option>
											<option>2019</option>
											<option>2020</option>

										</select>
									</div>

								</div>

								<div class="row">

									<div class="col-md-2">

										<span class="inlineEle">Disposed of by</span>
									</div>


									<div class="form-group col-md-4">

										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a9">
									</div>

									<div class="col-md-1">

										<span class="inlineEle">of</span>
									</div>
									<div class="form-group col-md-4">

										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a10">
									</div>

								</div>

								<div class="row">

									<div class="col-md-1">

										<span class="inlineEle">on the</span>
									</div>

									<div class="form-group col-md-5">

										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a11">
									</div>

									<div class="col-md-1">

										<span class="inlineEle">of the</span>
									</div>
									<div class="form-group col-md-5">

										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a12">
									</div>

								</div>

								<div class="row">

									<div class="form-group col-md-4 offset-md-4">

										<div class="md-form">
											<span><span>To,</span> <i
												class="fas fa-pencil-alt prefix col-md-5"></i> <textarea
													id="form10"
													class="md-textarea form-control col-md-offset-8" rows="5"
													ng-model="caseNotice.a13"></textarea>
										</div>
									</div>

								</div>

								<div class="row" style=>

									<div class="form-group col-md-3 col-md-offset-7">


										<input type="text" class="form-control" id="inputCity"
											ng-model="caseNotice.a14">

									</div>

									<div class="col-md-2">

										<span class="inlineEle">Opposite Party</span>
									</div>

								</div>

								<div class="row">

									<div class="form-group col-md-12 ">
										<h6>
											&nbsp; &nbsp; &nbsp; &nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Whereas the applicant
											abovementioned has made an application to this court that it
											may call for the record of above noted case and pass such
											order as it any think tit, you are hereby called upon to
											inter appearance on or before the <span> <input
												type="text" id="inputCity" ng-model="caseNotice.a15"></span>
											day of <input type="text" id="inputCity"
												ng-model="caseNotice.a16"> <input type="text"
												id="inputCity" ng-model="caseNotice.a17"> to answer
											the application. The said application will be heared by Court
											on such day thereafter as may subrequently notified in
											coordance with the rules.
										</h6>
									</div>

									<div class="form-group col-md-12 ">
										<h6>&nbsp; &nbsp; &nbsp; &nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Take Notice that in
											deafalut of appearance on or before the day before mentioned
											in person are by advocate or by some person by law authorised
											to act on your behalf the application will be heard and
											determined in your absence.</h6>
									</div>
									<div class="form-group col-md-12 ">
										<h6>
											&nbsp; &nbsp; &nbsp; &nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Given under my hand and
											the seal of court this day of <input type="text"
												id="inputCity" ng-model="caseNotice.a18"> <input
												type="text" id="inputCity" ng-model="caseNotice.a19">
										</h6>
									</div>
									<div class="form-group col-md-12 ">
										<h6>
											&nbsp; &nbsp; &nbsp; &nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Advocate : <input
												type="text" id="inputCity" ng-model="caseNotice.a20">
										</h6>
									</div>
									<div class="form-group col-md-5 ">
										<h6>
											Date - <input type="text" id="inputCity"
												ng-model="caseNotice.a21">

										</h6>

									</div>
									<div class="row">
										<div class="form-group col-md-4 col-md-offset-8 ">
											<h6 style="padding-right: 15px; padding-left: 15px;">
												Deputy Registrar <br> Allahabad/Lucknow

											</h6>
										</div>
									</div>

								</div>

								<div class="row">
									<div class="modal-footer"
										style="margin-left: 10px; margin-right: 10px;">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#exampleCaseModal1">Preview
											Notice</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


			<div class="modal fade" id="exampleCaseModal1" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Notice Form</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form>
								<h2 class="heading">In the High Court of Judicature at
									Allahabad</h2>
								<h5 class="heading">NOTICE OF REVISION</h5>
								<h6 class="heading">(Chapter XII, Rules 1 and 7)</h6>
								<h6 class="heading1">Revisional Jurisdiction</h6>
								<div class="row">
									<div class="form-group  col-md-2 col-md-offset-2 ">
										<span ng-if="caseNotice.a1!=undefined">
											{{caseNotice.a1}} </span> <span ng-if="caseNotice.a1==undefined">
											__________________ </span>
									</div>


									<div class="col-md-1 col-md-offset-0">

										<span class="inlineEle">No.</span>
									</div>


									<div class="col-md-3">

										<span ng-if="caseNotice.a2!=undefined">
											{{caseNotice.a2}} </span> <span ng-if="caseNotice.a2==undefined">
											__________________ </span>
									</div>
									<div class="col-md-1">

										<span class="inlineEle">of</span>
									</div>

									<div class="form-group  col-md-2 ">
										<span ng-if="caseNotice.a3!=undefined">
											{{caseNotice.a3}} </span> <span ng-if="caseNotice.a3==undefined">
											__________________ </span>
									</div>
								</div>

								<div class="row">
									<div class="form-group col-md-4 col-md-offset-4">

										<span ng-if="caseNotice.a4!=undefined">
											{{caseNotice.a4}} </span> <span ng-if="caseNotice.a4==undefined">
											__________________ </span>
									</div>

								</div>
								<div class="row">
									<div class="form-group col-md-4 col-md-offset-6 itemAlign">
										Versus</div>

								</div>
								<div class="row">
									<div class="form-group col-md-4 col-md-offset-4">

										<span ng-if="caseNotice.a5!=undefined">
											{{caseNotice.a5}} </span> <span ng-if="caseNotice.a5==undefined">
											__________________ </span>
									</div>

								</div>

								<div class="row">

									<div class="col-md-2">

										<span class="inlineEle">Arision our of</span>
									</div>

									<div class=" col-md-3">

										<span ng-if="caseNotice.a6!=undefined">
											{{caseNotice.a6}} </span> <span ng-if="caseNotice.a6==undefined">
											__________________ </span>
									</div>

									<div class="col-md-1">

										<span class="inlineEle">on</span>
									</div>
									<div class="col-md-3">

										<span ng-if="caseNotice.a7!=undefined">
											{{caseNotice.a7}} </span> <span ng-if="caseNotice.a7==undefined">
											__________________ </span>
									</div>

									<div class="col-md-1">

										<span class="inlineEle">of</span>
									</div>

									<div class="form-group  col-md-2">
										<span ng-if="caseNotice.a8!=undefined">
											{{caseNotice.a8}} </span> <span ng-if="caseNotice.a8==undefined">
											__________________ </span>
									</div>

								</div>

								<div class="row">


									<div class="col-md-2">

										<span class="inlineEle">Disposed of by</span>
									</div>


									<div class="form-group col-md-4">

										<span ng-if="caseNotice.a9!=undefined">
											{{caseNotice.a9}} </span> <span ng-if="caseNotice.a9==undefined">
											__________________ </span>
									</div>

									<div class="col-md-1">

										<span class="inlineEle">of</span>
									</div>
									<div class="form-group col-md-4">

										<span ng-if="caseNotice.a10!=undefined">
											{{caseNotice.a10}} </span> <span ng-if="caseNotice.a10==undefined">
											__________________ </span>
									</div>

								</div>

								<div class="row">

									<div class="col-md-1">

										<span class="inlineEle">on the</span>
									</div>

									<div class="form-group col-md-5">

										<span ng-if="caseNotice.a11!=undefined">
											{{caseNotice.a11}} </span> <span ng-if="caseNotice.a11==undefined">
											__________________ </span>
									</div>

									<div class="col-md-1">

										<span class="inlineEle">of the</span>
									</div>
									<div class="form-group col-md-5">

										<span ng-if="caseNotice.a12!=undefined">
											{{caseNotice.a12}} </span> <span ng-if="caseNotice.a12==undefined">
											__________________ </span>
									</div>

								</div>
								<!-- 	<div class="row">

							<div class="form-group col-md-4 offset-md-4">

								<div class="md-form">
									<span><span>To,</span> <i
										class="fas fa-pencil-alt prefix"></i> <span
										ng-if="caseNotice.a13!=undefined"> {{caseNotice.a13}} </span>
										<span ng-if="caseNotice.a13==undefined">
											__________________ </span>
								</div>
							</div>

						</div> -->
								<div class="row">
									<div class="form-group col-md-4 offset-md-4"
										ng-if="caseNotice.a13">
										<div class="md-form">
											<span>To,</span> <i class="fas fa-pencil-alt prefix col-md-5"></i>
											<textarea style="border: none" ng-if="caseNotice.a13"
												id="form21" class="md-textarea form-control col-md-offset-8"
												rows="5" ng-model="caseNotice.a13" readonly></textarea>
											<!-- <textarea ng-if="!applicationNotice.a7" id="form11sss" class="md-textarea form-control col-md-offset-8" rows="8" readonly
											 <span ng-if="!applicationNotice.a7">
									__________________ </span>
								
											</textarea> -->

										</div>


									</div>
									<div class="form-group col-md-4 offset-md-4"
										ng-if="!caseNotice.a13">
										<div class="md-form">
											<span>To,</span> <i class="fas fa-pencil-alt prefix col-md-5"></i>


											<span ng-if="!caseNotice.a13"> __________________ </span>


										</div>
									</div>
								</div>

								<div class="row">

									<div class="form-group col-md-3 col-md-offset-7">


										<span ng-if="caseNotice.a14!=undefined">
											{{caseNotice.a14}} </span> <span ng-if="caseNotice.a14==undefined">
											__________________ </span>

									</div>
									<div class="col-md-2">

										<span class="inlineEle">Opposite Party</span>
									</div>

								</div>
								<div class="row">

									<div class="form-group col-md-12 ">
										<h6>
											&nbsp; &nbsp; &nbsp; &nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Whereas the applicant
											abovementioned has made an application to this court that it
											may call for the record of above noted case and pass such
											order as it any think tit, you are hereby called upon to
											inter appearance on or before the <span
												ng-if="caseNotice.a15!=undefined"> {{caseNotice.a15}}
											</span> <span ng-if="caseNotice.a15==undefined">
												__________________ </span> day of <span
												ng-if="caseNotice.a16!=undefined"> {{caseNotice.a16}}
											</span> <span ng-if="caseNotice.a16==undefined">
												__________________ </span> <span ng-if="caseNotice.a17!=undefined">
												{{caseNotice.a17}} </span> <span ng-if="caseNotice.a17==undefined">
												__________________ </span> to answer the application. The said
											application will be heared by Court on such day thereafter as
											may subrequently notified in coordance with the rules.
										</h6>
									</div>

									<div class="form-group col-md-12 ">
										<h6>&nbsp; &nbsp; &nbsp; &nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Take Notice that in
											deafalut of appearance on or before the day before mentioned
											in person are by advocate or by some person by law authorised
											to act on your behalf the application will be heard and
											determined in your absence.</h6>
									</div>
									<div class="form-group col-md-12 ">
										<h6>
											&nbsp; &nbsp; &nbsp; &nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Given under my hand and
											the seal of court this day of <span
												ng-if="caseNotice.a18!=undefined"> {{caseNotice.a18}}
											</span> <span ng-if="caseNotice.a18==undefined">
												__________________ </span> <span ng-if="caseNotice.a19!=undefined">
												{{caseNotice.a19}} </span> <span ng-if="caseNotice.a19==undefined">
												__________________ </span>
										</h6>
									</div>
									<div class="form-group col-md-12 ">
										<h6>
											&nbsp; &nbsp; &nbsp; &nbsp;
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Advocate : <span
												ng-if="caseNotice.a20!=undefined"> {{caseNotice.a20}}
											</span> <span ng-if="caseNotice.a20==undefined">
												__________________ </span>
										</h6>
									</div>
									<div class="form-group col-md-5 ">
										<h6>
											Date - <span ng-if="caseNotice.a21!=undefined">
												{{caseNotice.a21}} </span> <span ng-if="caseNotice.a21==undefined">
												__________________ </span>

										</h6>

									</div>
									<div class="row">
										<div class="form-group col-md-4 col-md-offset-8 ">
											<h6 style="padding-right: 15px; padding-left: 15px;">
												Deputy Registrar <br> Allahabad/Lucknow

											</h6>
										</div>
									</div>

								</div>

								<!-- <button type="submit" class="btn btn-primary" ng-click="submitForm()">Sign in</button> -->
								<div class="row">
									<div class="modal-footer"
										style="margin-left: 10px; margin-right: 10px;">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary"
											ng-click="submitForm()">Submit Notice</button>
									</div>
								</div>
							</form>
						</div>
						<!-- <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div> -->
					</div>
				</div>
			</div>



			<div class="row">
				<!-- begin col-12 -->
				<!-- begin panel -->
				<div class="panel panel-inverse">
					<div class="panel-heading">
						<div class="panel-heading-btn">
							<a href="javascript:;"
								class="btn btn-xs btn-icon btn-circle btn-default"
								data-click="panel-expand"><i class="fa fa-expand"></i></a>
						</div>
						<h6 class="panel-title">Send Notice</h6>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table id="data-table" st-table="displayedCollection"
								st-safe-src="masterdata"
								class="table table-striped table-bordered nowrap table-hover"
								width="100%">
								<thead>
									<tr>
										<td width="25%"><select class="form-control"
											ng-model="search.fd_case_type"
											ng-options="caseType.ct_id as caseType.ct_name for caseType in caseTypes  | orderBy:'ct_name'">
												<option value="">Select Case Type</option>
										</select></td>
										<td><input type="text" class="form-control"
											placeholder="Case No" ng-model="search.fd_case_no"></td>
										<td><input type="text" class="form-control"
											placeholder="Case Year" ng-model="search.fd_case_year">
										</td>
										<td>
											<button id="search" type="submit"
												class="btn btn-primary btn-sm" ng-click="searchCaseFiles()">Search</button>
										</td>
									</tr>

								</thead>
							</table>
							<table id="data-table" class="table table-striped table-bordered">
								<thead>
									<tr>
										<th style="width: 1%;">Sr.No.</th>
										<th>Case Type</th>
										<th>Case No</th>
										<th>Case Year</th>
										<th>Exist in Efiling</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="row in caseFileList" class="odd gradeX">
										<td>{{$index+1}}</td>
										<td>{{row.caseType.ct_name}}</td>
										<td>{{row.fd_case_no}}</td>
										<td>{{row.fd_case_year}}</td>
										<td align="center">{{dmsCaseData.status}}</td>
										<td>
											<%
												if (role.equals("DMSAdmin")) {
											%>
											<button class="btn btn-success btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button>
											<button class="btn btn-success btn-sm"
												ng-click="viewDetail(row.fd_id)">View Detail</button>
											<button class="btn btn-success btn-sm"
												ng-click="downloadFiles(row.fd_id)">Download</button>

											<button class="btn btn-success btn-sm"
												ng-click="setModel(row)" data-toggle="modal"
												data-target="#updateCaseType">Change Case Type</button>
											<button type="button" class="btn btn-success btn-sm"
												data-toggle="modal" ng-click="setModel(row)"
												data-target="#uploadDocument">Upload</button>

											<button class="btn btn-success btn-sm"
												ng-click="setModel(row)" data-toggle="modal"
												data-target="#caseAssignTo">Assign To</button>
											<button class="btn btn-success btn-sm"
												ng-click="setModel(row)" data-toggle="modal"
												data-target="#addcaseefiling">AddCaseToEfiling</button> <%
 	}
 %>

											<%
												if (role.equals("Review_Officer") || role.equals("Assistant Review Officer")) {
											%>
											<!--  <button type="button" class="btn btn-success btn-sm" data-toggle="modal" ng-click="setModel(row)" data-target="#uploadDocument">Upload
                                                            </button> -->
											<button class="btn btn-success btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button>
											<button type="button" class="btn btn-success btn-sm"
												data-toggle="modal" ng-click="setModel(row)"
												data-target="#uploadDocument">Upload</button>
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#exampleModal">Add
												Application Notice</button>
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#exampleCaseModal">Add
												Case Notice</button> <%
 	}
 %> <%
 	if (role.equals("PS")) {
 %>
											<button class="btn btn-success btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button> <%
 	}
 %> <%
 	if (role.equals("Judge")) {
 %>
											<button class="btn btn-success btn-sm"
												ng-click="viewCaseFile(row.fd_id)">View</button> <%
 	}
 %>

										</td>
									</tr>
									<tr ng-show="caseFileList.length==0">
										<td colspan="8">
											<div class="alert alert-danger">No Records Found</div> <%--  <% if(role.equals("DMSAdmin")) {%>
                                                <button class="btn btn-success btn-sm" ng-click="setModel(row)" data-toggle="modal"  data-target="#addcaseefiling">AddCaseToEfiling</button>
                                                <% }%> --%>

										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="modal fade" id="addcaseefiling" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h6 class="modal-title" id="myModalLabel">
											<strong>Upload Notice</strong>
										</h6>
									</div>
									<%@ include file="../casefile/addCaseEfiling.jsp"%>
								</div>
							</div>
						</div>
						<div class="modal fade" id="uploadDocument" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h6 class="modal-title" id="myModalLabel">
											<strong>Upload Document</strong>
										</h6>
									</div>
									<%@ include file="../notice/_upload_form.jsp"%>
								</div>
							</div>
						</div>
						<div class="modal fade" id="viewFiles" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h6 class="modal-title" id="myModalLabel">
											<strong>Stage History</strong>
										</h6>
									</div>
									<%@ include file="../casefile/filelist.jsp"%>
								</div>
							</div>
						</div>
						<div class="modal fade" id="updateCaseType" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h6 class="modal-title" id="myModalLabel">
											<strong>Update Case Type</strong>
										</h6>
									</div>
									<%@ include file="../casefile/updateCasetype.jsp"%>
								</div>
							</div>
						</div>

						<div class="modal fade" id="caseAssignTo" tabindex="-1"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h6 class="modal-title" id="myModalLabel">
											<strong>Case Assign To</strong>
										</h6>
									</div>
									<%@ include file="../casefile/caseAssignTo.jsp"%>
								</div>
							</div>
						</div>

					</div>
				</div>

				<!-- end panel -->

				<!-- end col-12 -->
			</div>

		</div>
	</div>

	<!-- end row -->
</body>

<!-- ================== END PAGE LEVEL JS ================== -->
<link rel="stylesheet" href="../css/notice/notice.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ng-file-upload.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/angularJs/ngMask.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/notice/NoticeController.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/angular-datepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls.0.11.2.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/apps.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scripts/controllers/editor.js"></script>

<script>
        $(document).ready(function() {
            $("#txtEditor").Editor();
            App.init();

        });
    </script>
<!-- <link href="editor.css" type="text/css" rel="stylesheet"/> -->

</html>