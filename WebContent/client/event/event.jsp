<!-- 
# 작성자 : 이지수
# 작성일 : 2013.07.31 
-->

<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../include/header.jsp" %>

<link rel="stylesheet" type="text/css" href="./css/event.css" />

<div class="clear"></div>

<div id="content_wrapper">

<%@ include file="../include/left_menu.jsp" %>

	<div id="content_c">

	<p id="title">금주의 이벤트 - 포인트를 잡아라!</p>
	
	<div id="descPage">
		<p>도란도란을 사랑하는 회원 여러분~ 포인트 받아가세요!<br>
		   도란도란이 준비한 금주의 이벤트는 "잡아라! 포인트 룰렛" 입니다. 기대기대!!<br>
		   방법은 정말 간단해요~ 룰렛 위에 마우스를 올리고 원하는 방향(왼쪽 또는 오른쪽)으로 돌려주세요.<br> 
		   너무나 쉽게 포인트를 얻을 수 있는 기회이니~ 꼭꼭! 도전해보세요~ 꽝이 없답니다~^^
		</p>
	</div>
	
	<div id="contentArea">
	
	<div id="gamePan">
		
	<div id="container"></div>
	
    <script src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v4.5.5.min.js"></script>
    <script defer="defer">
      var angularVelocity = 6;
      var angularVelocities = [];
      var lastRotations = 0;
      var controlled = false;
      var numWedges = 25;
      var angularFriction = 0.2;
      var target, activeWedge, stage, layer, wheel, pointer;

      function getAverageAngularVelocity() {
        var total = 0;
        var len = angularVelocities.length;

        if(len === 0) {
          return 0;
        }

        for(var n = 0; n < len; n++) {
          total += angularVelocities[n];
        }

        return total / len;
      }
      function purifyColor(color) {
        var randIndex = Math.round(Math.random() * 3);
        color[randIndex] = 0;
        return color;
      }
      function getRandomColor() {
        var r = 100 + Math.round(Math.random() * 55);
        var g = 100 + Math.round(Math.random() * 55);
        var b = 100 + Math.round(Math.random() * 55);
        var color = [r, g, b];
        color = purifyColor(color);
        color = purifyColor(color);

        return color;
      }
      function bind() {
        wheel.on('mousedown', function(evt) {
          angularVelocity = 0;
          controlled = true;
          target = evt.targetNode;
        });
        // add listeners to container
        document.body.addEventListener('mouseup', function() {
          controlled = false;
          angularVelocity = getAverageAngularVelocity() * 5;

          if(angularVelocity > 20) {
            angularVelocity = 20;
          }
          else if(angularVelocity < -20) {
            angularVelocity = -20;
          }

          angularVelocities = [];
        }, false);

        document.body.addEventListener('mousemove', function(evt) {
          var mousePos = stage.getMousePosition();
          if(controlled && mousePos && target) {
            var x = mousePos.x - wheel.getX();
            var y = mousePos.y - wheel.getY();
            var atan = Math.atan(y / x);
            var rotation = x >= 0 ? atan : atan + Math.PI;
            var targetGroup = target.getParent();

            wheel.setRotation(rotation - targetGroup.startRotation - (target.getAngle() / 2));
          }
        }, false);
      }
      function getRandomReward() {
        var mainDigit = Math.round(Math.random() * 9);
        return mainDigit + '\n0\n0';
      }
      function addWedge(n) {
        var s = getRandomColor();
        var reward = getRandomReward();
        var r = s[0];
        var g = s[1];
        var b = s[2];
        var angle = 2 * Math.PI / numWedges;

        var endColor = 'rgb(' + r + ',' + g + ',' + b + ')';
        r += 100;
        g += 100;
        b += 100;

        var startColor = 'rgb(' + r + ',' + g + ',' + b + ')';

        var wedge = new Kinetic.Group({
          rotation: 2 * n * Math.PI / numWedges,
        });

        var wedgeBackground = new Kinetic.Wedge({
          radius: 400,
          angle: angle,
          fillRadialGradientStartPoint: 0,
          fillRadialGradientStartRadius: 0,
          fillRadialGradientEndPoint: 0,
          fillRadialGradientEndRadius: 400,
          fillRadialGradientColorStops: [0, startColor, 1, endColor],
          fill: '#64e9f8',
          fillPriority: 'radial-gradient',
          stroke: '#ccc',
          strokeWidth: 2
        });

        wedge.add(wedgeBackground);

        var text = new Kinetic.Text({
          text: reward,
          fontFamily: 'Calibri',
          fontSize: 50,
          fill: 'white',
          align: 'center',
          stroke: 'yellow',
          strokeWidth: 1

        });

        // cache text as an image to improve performance
        text.toImage({
          width: text.getWidth(),
          height: text.getHeight(),
          callback: function(img) {
            var cachedText = new Kinetic.Image({
              image: img,
              listening: false,
              rotation: (Math.PI + angle) / 2,
              x: 380,
              y: 30
            });

            wedge.add(cachedText);
            layer.draw();
          }
        });

        wedge.startRotation = wedge.getRotation();

        wheel.add(wedge);
      }
      function animate(frame) {
        // handle wheel spin
        var angularVelocityChange = angularVelocity * frame.timeDiff * (1 - angularFriction) / 1000;
        angularVelocity -= angularVelocityChange;

        if(controlled) {
          if(angularVelocities.length > 10) {
            angularVelocities.shift();
          }
          
          angularVelocities.push((wheel.getRotation() - lastRotation) * 1000 / frame.timeDiff);
        }
        else {
          wheel.rotate(frame.timeDiff * angularVelocity / 1000);
        }
        lastRotation = wheel.getRotation();

        // activate / deactivate wedges based on point intersection
        var intersection = stage.getIntersection({
          x: stage.getWidth() / 2,
          y: 100
        });

        if(intersection) {
          var shape = intersection.shape;

          if(shape && (!activeWedge || (shape._id !== activeWedge._id))) {
            pointer.setY(20);
            
            new Kinetic.Tween({
              node: pointer, 
              duration: 0.3,
              y: 30,
              easing: Kinetic.Easings.ElasticEaseOut
            }).play();

            if(activeWedge) {
              activeWedge.setFillPriority('radial-gradient');
            }
            shape.setFillPriority('fill');
            activeWedge = shape;
          }
        }
      }
      function init() {
        stage = new Kinetic.Stage({
          container: 'container',
          width: 578,
          height: 200
        });
        layer = new Kinetic.Layer();
        wheel = new Kinetic.Group({
          x: stage.getWidth() / 2,
          y: 410
        });

        for(var n = 0; n < numWedges; n++) {
          addWedge(n);
        }
        pointer = new Kinetic.Wedge({
          fillRadialGradientStartPoint: 0,
          fillRadialGradientStartRadius: 0,
          fillRadialGradientEndPoint: 0,
          fillRadialGradientEndRadius: 30,
          fillRadialGradientColorStops: [0, 'white', 1, 'red'],
          stroke: 'white',
          strokeWidth: 2,
          lineJoin: 'round',
          angleDeg: 30,
          radius: 30,
          x: stage.getWidth() / 2,
          y: 30,
          rotationDeg: -105,
          shadowColor: 'black',
          shadowOffset: 3,
          shadowBlur: 2,
          shadowOpacity: 0.5
        });

        // add components to the stage
        layer.add(wheel);
        layer.add(pointer);
        stage.add(layer);

        // bind events
        bind();

        var anim = new Kinetic.Animation(animate, layer);

        // wait one second and then spin the wheel
        setTimeout(function() {
          anim.start();
        }, 1000);
      }
      init();

    </script>
    
    </div><!-- end gamepan -->
    
    </div><!-- end contentArea -->

	</div><!-- end content_c -->
</div><!-- end content_wrapper -->

<div class="clear"></div>

<%@ include file="../include/footer.jsp" %>