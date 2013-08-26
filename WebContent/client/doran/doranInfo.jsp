<!-- 
# 작성자 : 이지수
# 작성일 : 2013.08.07 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/doran.css" />
<script src="./js/doran.js"></script>

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">

	<p id="title"><span id="siteName">▧  도란 도란 공동구매 샵  ▨</span>&nbsp;&nbsp;&nbsp;회사 소개 및 찾아오시는 길</p>
	
	<div id="contentArea">
	
	<div id="siteInfo">	
		<p class="infoTitle">1. 사이트 명의 의미는?</p>
			<ul>
				<li>“도란도란” 이란 순수 우리말로 ‘여럿이 나직한 목소리로 서로 정답게 이야기하는 소리 또는 그 모양’을 뜻합니다.</li> 
				<li>중소기업과 소비자가 어울려져 정다운 쇼핑몰을 만들어나가겠다는 취지입니다</li>
			</ul>
		<p class="infoTitle">2. 사이트 명의 탄생 배경은?</p>
			<ul>
				<li>현재 우리나라의 영세한 중소기업은 거대 기업 위주의 광고 시장을 선점하기에 어려운 환경입니다.</li>
				<li>그렇기에 소비자들은 저렴하면서도 질 좋은 중소기업의 제품을 알 기회조차 부족한 상황입니다.</li>
				<li>따라서 경쟁력 있는 제품을 개발하였어도 매출부진으로 폐업을 하는 중소기업들이 많은 현실입니다.</li>
			</ul>
		
		<p class="infoTitle">3. 사이트 명의 운영 원칙은?</p>
			<ul>
				<li>매주 공동구매 할 1개의 아이템(종목) 선정<br>
				&nbsp;&nbsp;&nbsp;- 아이템 선정은 매주 소비자(회원)들을 대상으로 설문조사 실시
				 </li>
				<li>공동구매에 참여할 중소기업 3곳 선정<br>
				&nbsp;&nbsp;&nbsp;- 참여 의사가 있는 기업은 입점 요청서를 작성하여 제출</li>
				<li>소비자는 3곳의 다른 회사 제품 중 원하는 제품 선택 가능</li>
				<li>제품 상세 정보 페이지에 필요한 광고 컨텐츠는 선정된 중소기업에서 제공</li>
				<li>월~금까지 “금주의 공동구매” 진행, 주말까지 결제 가능(직장인들을 위해)</li>
				<li>소비자들은 커뮤니티 공간을 통해 제품에 대한 다양한 의견 교류</li>
				<li>공동구매 쇼핑몰과 중소기업 수입 배분(20 : 80) </li>				
			</ul>
		<p class="infoTitle">4. 사이트를 통한 기대 효과는?</p>
			<ul>
				<li>중소기업에는 서로 경쟁하여 소비자들에게 자사 제품을 알릴 수 있는 홍보의 장 마련</li>
				<li>소비자에게는 공동구매를 통해 적은 비용으로 높은 만족을 얻을 수 있는 기회 제공</li>
				<li>커뮤니티를 통해 기업과 소비자가 능동적으로 참여할 수 있는 오픈형 공간 마련</li>
			</ul>
			
		<p class="infoTitle">5. "도란도란 공동구매 샵"에 찾아오시려면?</p>	
			<ul>
				<li>주소 : 서울특별시 강남구 역삼동 826-22</li>
				<li>연락처 : 02-861-1811</li>
			</ul>
	</div><!-- end siteInfo -->
	
	<div id="mapArea">
	
	<script type="text/javascript" src="http://openapi.map.naver.com/openapi/naverMap.naver?ver=2.0&key=5575a051937cacbef15a48ccfecf1ccc"></script>
	<div id="map" style="border:1px solid #000;"></div>
	<script type="text/javascript">
		var oSeoulCityPoint = new nhn.api.map.LatLng(37.5564904, 126.9451942);
		var defaultLevel = 11;
		var oMap = new nhn.api.map.Map(document.getElementById('map'), { 
						point : oSeoulCityPoint,
						zoom : defaultLevel,
						enableWheelZoom : true,
						enableDragPan : true,
						enableDblClickZoom : false,
						mapMode : 0,
						activateTrafficMap : false,
						activateBicycleMap : false,
						minMaxLevel : [ 1, 14 ],
						size : new nhn.api.map.Size(600, 380)		});
		var oSlider = new nhn.api.map.ZoomControl();
		oMap.addControl(oSlider);
		oSlider.setPosition({
			top : 10,
			left : 10
		});

		var oMapTypeBtn = new nhn.api.map.MapTypeBtn();
		oMap.addControl(oMapTypeBtn);
		oMapTypeBtn.setPosition({
			bottom : 10,
			right : 80
		});
		
		var oThemeMapBtn = new nhn.api.map.ThemeMapBtn();
		oThemeMapBtn.setPosition({
			bottom : 10,
			right : 10
		});
		oMap.addControl(oThemeMapBtn);

		var oBicycleGuide = new nhn.api.map.BicycleGuide(); // - 자전거 범례 선언
		oBicycleGuide.setPosition({
			top : 10,
			right : 10
		}); // - 자전거 범례 위치 지정
		oMap.addControl(oBicycleGuide);// - 자전거 범례를 지도에 추가.

		var oTrafficGuide = new nhn.api.map.TrafficGuide(); // - 교통 범례 선언
		oTrafficGuide.setPosition({
			bottom : 30,
			left : 10
		});  // - 교통 범례 위치 지정.
		oMap.addControl(oTrafficGuide); // - 교통 범례를 지도에 추가.

		var trafficButton = new nhn.api.map.TrafficMapBtn(); // - 실시간 교통지도 버튼 선언
		trafficButton.setPosition({
			bottom:10, 
			right:150
		}); // - 실시간 교통지도 버튼 위치 지정
		oMap.addControl(trafficButton);

		var oSize = new nhn.api.map.Size(28, 37);
		var oOffset = new nhn.api.map.Size(14, 37);
		var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);

		var oInfoWnd = new nhn.api.map.InfoWindow();
		oInfoWnd.setVisible(false);
		oMap.addOverlay(oInfoWnd);

		oInfoWnd.setPosition({
			top : 20,
			left :20
		});

		var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
		oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨.

		oInfoWnd.attach('changeVisible', function(oCustomEvent) {
			if (oCustomEvent.visible) {
				oLabel.setVisible(false);
			}
		});
		
		var oPolyline = new nhn.api.map.Polyline([], {
			strokeColor : '#f00', // - 선의 색깔
			strokeWidth : 5, // - 선의 두께
			strokeOpacity : 0.5 // - 선의 투명도
		}); // - polyline 선언, 첫번째 인자는 선이 그려질 점의 위치. 현재는 없음.
		oMap.addOverlay(oPolyline); // - 지도에 선을 추가함.

		oMap.attach('mouseenter', function(oCustomEvent) {

			var oTarget = oCustomEvent.target;
			// 마커위에 마우스 올라간거면
			if (oTarget instanceof nhn.api.map.Marker) {
				var oMarker = oTarget;
				oLabel.setVisible(true, oMarker); // - 특정 마커를 지정하여 해당 마커의 title을 보여준다.
			}
		});

		oMap.attach('mouseleave', function(oCustomEvent) {

			var oTarget = oCustomEvent.target;
			// 마커위에서 마우스 나간거면
			if (oTarget instanceof nhn.api.map.Marker) {
				oLabel.setVisible(false);
			}
		});

		oMap.attach('click', function(oCustomEvent) {
			var oPoint = oCustomEvent.point;
			var oTarget = oCustomEvent.target;
			oInfoWnd.setVisible(false);
			// 마커 클릭하면
			if (oTarget instanceof nhn.api.map.Marker) {
				// 겹침 마커 클릭한거면
				if (oCustomEvent.clickCoveredMarker) {
					return;
				}
				// - InfoWindow 에 들어갈 내용은 setContent 로 자유롭게 넣을 수 있습니다. 외부 css를 이용할 수 있으며, 
				// - 외부 css에 선언된 class를 이용하면 해당 class의 스타일을 바로 적용할 수 있습니다.
				// - 단, DIV 의 position style 은 absolute 가 되면 안되며, 
				// - absolute 의 경우 autoPosition 이 동작하지 않습니다. 
				oInfoWnd.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'+
					'<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 5px 2px 2px !important">' + 
					'Hello World <br /> ' + oTarget.getPoint()
					+'<span></div>');
				oInfoWnd.setPoint(oTarget.getPoint());
				oInfoWnd.setPosition({right : 15, top : 30});
				oInfoWnd.setVisible(true);
				oInfoWnd.autoPosition();
				return;
			}
			var oMarker = new nhn.api.map.Marker(oIcon, { title : '도란도란 공동구매 샵 '});
			oMarker.setPoint(oPoint);
			oMap.addOverlay(oMarker);

			var aPoints = oPolyline.getPoints(); // - 현재 폴리라인을 이루는 점을 가져와서 배열에 저장.
			aPoints.push(oPoint); // - 추가하고자 하는 점을 추가하여 배열로 저장함.
			oPolyline.setPoints(aPoints); // - 해당 폴리라인에 배열에 저장된 점을 추가함
		});
	</script>
	
	</div>
		

	</div><!-- end contentArea -->

	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>