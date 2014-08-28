// ͼƬ�����б� mengjia 070816

var Speed = 180;// �ٶ�(����)
var Space = 5;// ÿ���ƶ�(px)
var fill = 0;// ������λ
var PageWidth = 528;// ��ҳ���
var Comp = 0;
var MoveLock = false;// �Ƿ��ƶ� Ĭ��false
var MoveTimeObj = null;// ʱ�������ʱ��
var AutoPlayObj = null;// �Զ�������ʱ��

// ���һ�β�������ť����
var index_leftButton = 0;
// ���һ�β������Ұ�ť����
var index_RightButton = 0;

$(function() {
	// �ҷ�--->
	$(".RightBotton").click(function() {
				scrollRight(this);
			}).mouseout(function() {
				// stopScrollRight(this);
			});
	// ��<----
	$(".LeftBotton").mousedown(function() {
				index_leftButton = $(".LeftBotton").index(this);
				alert(index_leftButton);
				scrollLeft(this);
			}).mouseout(function() {
				// ISL_StopUp(this);
			});

	// ����Ԫ��
	$(".Cont").mouseover(function() {
				clearInterval(AutoPlayObj);
			}).mouseout(function() {
				// AutoPlay();
			});

		/**
		 * �Զ�����
		 */
		// AutoPlay();
	});

// ********************************************************************************
// �·�
function scrollRight(obj) {
	// �ر��ƶ���ʱ��
	clearInterval(MoveTimeObj);
	// ����ƶ�,return
	// if (MoveLock)
	// return;
	// �ر��Զ�������ʱ��
	clearInterval(AutoPlayObj);
	// ��ʶ��ʼ�ƶ�
	MoveLock = true;

	// ������ʱ��
	MoveTimeObj = setInterval(function() {

				// �������� ���뻺��
				var JQscroolDiv = $(obj).prev(".Cont");
				// �����б�
				var JQscroolDivList = JQscroolDiv.find(".div_scroolList");
				// ���ƫ����
				var JQscroolDiv_scrollLeft = JQscroolDiv.scrollLeft();
				// �б������ܿ��
				var JQscroolDivList_scrollWidth = JQscroolDivList[0].scrollWidth;

				if (JQscroolDiv_scrollLeft >= JQscroolDivList_scrollWidth) {
					JQscroolDiv.scrollLeft(JQscroolDiv_scrollLeft - JQscroolDivList_scrollWidth);
				}
				JQscroolDiv.scrollLeft(JQscroolDiv.scrollLeft() + Space);
			}, Speed);
}

// �·�ֹͣ
function stopScrollRight(obj) {
	clearInterval(MoveTimeObj);
	// �������� ���뻺��
	var JQscroolDiv = $(obj).prev(".Cont");
	// ���ƫ����
	var JQscroolDiv_scrollLeft = JQscroolDiv.scrollLeft();
	if (JQscroolDiv_scrollLeft % PageWidth - fill != 0) {
		Comp = PageWidth - JQscroolDiv_scrollLeft % PageWidth + fill;
		setInterval(function() {
					var num;
					if (Comp == 0) {
						MoveLock = false;
						return;
					}
					if (Comp > Space) {
						Comp -= Space;
						num = Space;
					} else {
						num = Comp;
						Comp = 0;
					}
					JQscroolDiv.scrollLeft(JQscroolDiv_scrollLeft + num);
				}, Speed);
	} else {
		MoveLock = false;
	}
	// AutoPlay();
}

// *********************************************************************************
// �Ϸ���ʼ
function scrollLeft(obj) {
	// if (MoveLock)
	// return;
	clearInterval(MoveTimeObj);
	clearInterval(AutoPlayObj);
	MoveLock = true;
	MoveTimeObj = setInterval(function() {
				// �������� ���뻺��
				var JQscroolDiv = $(obj).next(".Cont");
				// �����б�
				var JQscroolDivList = JQscroolDiv.find(".div_scroolList");
				// ���ƫ����
				var JQscroolDiv_scrollLeft = JQscroolDiv.scrollLeft();
				// �б������ܿ��
				var JQscroolDivList_offsetWidth = JQscroolDivList[0].offsetWidth;
				if (JQscroolDiv_scrollLeft <= 0) {
					JQscroolDiv.scrollLeft(JQscroolDiv_scrollLeft + JQscroolDivList_offsetWidth);
				}
				JQscroolDiv.scrollLeft(JQscroolDiv.scrollLeft() - Space);
			}, Speed);
}
// �Ϸ�ֹͣ
function ISL_StopUp() {
	clearInterval(MoveTimeObj);
	// �������� ���뻺��
	var JQscroolDiv = $(obj).next(".Cont");
	// ���ƫ����
	var JQscroolDiv_scrollLeft = JQscroolDiv.scrollLeft();
	if (JQscroolDiv_scrollLeft % PageWidth - fill != 0) {
		Comp = fill - (JQscroolDiv_scrollLeft % PageWidth);
		setInterval(function() {
					var num;
					if (Comp == 0) {
						MoveLock = false;
						return;
					}
					if (Comp < -Space) {
						Comp += Space;
						num = Space;
					} else {
						num = -Comp;
						Comp = 0;
					}
					JQscroolDiv.scrollLeft(JQscroolDiv_scrollLeft - num);
				}, Speed);
	} else {
		MoveLock = false;
	}
	// AutoPlay();
}

// ********************************************************************************
// �Զ�����
function AutoPlay() {
	clearInterval(AutoPlayObj);
	AutoPlayObj = setInterval('scrollRight();stopScrollRight();', 3000); // ���ʱ��
}