/**
 * <pre>
 * ����
 * :   ͼƬ�޷����(�������޸���)
 *     	Company :   ����
 *      ����		:  	����(���챼��) venusdrogon@163.com
 *     	�ο�		:	ͼƬ�����б� mengjia 070816
 * 
 * �޸�ʱ��:
 *      ��һ��	:	2010-10-19  ����(���챼��)
 *      �ڶ���	:	2010-10-20 10:44 �����Զ�����
 * </pre>
 */
/** ********************************************************* */
/**
 * �ٶ�(����)
 * 
 * 
 */
var Speed = 1;
/**
 * ÿ���ƶ�(px)
 * 
 * 
 */
var Space = 5;
/**
 * ��ҳ���
 * 
 * 
 */
var PageWidth = 528;
/**
 * ������λ
 * 
 * 
 */
var fill = 0;
var Comp = 0;

var MoveLock = false;

var MoveTimeObj = null;
var AutoPlayObj = null;

// ��ǰ�����Ĺ�����
var _currentScrollDiv = null;

/**
 * �������className
 * 
 * 
 */
var className_scrollDiv = ".Cont";
// 
/**
 * ��ʾ�������ݵ�div className
 * 
 * 
 */
var className_listDiv = ".List1";
// *************************************
$(function() {

			$(".List2").html(function(index, html) {
						return $(this).prev(className_listDiv).html();
					});

			/**
			 * �����������¼�
			 */
			$(className_scrollDiv).scrollLeft(fill).mouseover(function() {
						_currentScrollDiv = $(this);
						clearInterval(AutoPlayObj);
					}).mouseout(function() {
						AutoPlay();
					});

			// �ҷ�--->
			$(".RightBotton").mousedown(function() {
						_currentScrollDiv = $(this).prev(className_scrollDiv);
						ISL_GoDown(_currentScrollDiv);
					}).mouseout(function() {
						_currentScrollDiv = $(this).prev(className_scrollDiv);
						scrollRight_stop(_currentScrollDiv);
					}).mouseup(function() {
						_currentScrollDiv = $(this).prev(className_scrollDiv);
						scrollRight_stop(_currentScrollDiv);
					});
			// ��<---
			$(".LeftBotton").mousedown(function() {
						_currentScrollDiv = $(this).next(className_scrollDiv);
						ISL_GoUp(_currentScrollDiv);
					}).mouseout(function() {
						_currentScrollDiv = $(this).next(className_scrollDiv);
						ISL_StopUp(_currentScrollDiv);
					}).mouseup(function() {
						_currentScrollDiv = $(this).next(className_scrollDiv);
						ISL_StopUp(_currentScrollDiv);
					});

			/**
			 * �Զ�����
			 */
			AutoPlay();

		});

// ************************�Զ�����************************************
// �Զ�����
function AutoPlay() {
	clearInterval(AutoPlayObj);
	AutoPlayObj = setInterval(function() {
				ISL_GoDown();
				scrollRight_stop();
			}, 3000); // ���ʱ��
}
// ***************************�·�*********************************
// 
/**
 * �·� ����
 * 
 * @param {jquery����}
 *            JQObjScroll ��Ҫ�����Ĳ�(jquery����)
 */
function ISL_GoDown() {
	// $("#span_test").html(parseInt($("#span_test").html(), 10) +1).css("color", "#FF00FF");
	clearInterval(MoveTimeObj);
	if (MoveLock)
		return;
	clearInterval(AutoPlayObj);
	MoveLock = true;
	if (null == _currentScrollDiv) {
		_currentScrollDiv = $($(className_scrollDiv)[0]);
	}
	// �Զ�����ʱ�ȵ���һ��
	scrollRight();
	MoveTimeObj = setInterval(scrollRight, Speed);
}

/**
 * ���ƺ��ķ���
 */
function scrollRight() {
	// ����Ԫ�� ��scrollLeft
	var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
	// ����Ԫ�� �����list1Ԫ�� ��scrollWidth
	var JQObjScrollList1_scrollWidth = _currentScrollDiv.find(className_listDiv).get(0).scrollWidth;
	if (JQObjScroll_scrollLeft >= JQObjScrollList1_scrollWidth) {
		_currentScrollDiv.get(0).scrollLeft = JQObjScroll_scrollLeft - JQObjScrollList1_scrollWidth;
	}
	_currentScrollDiv.get(0).scrollLeft = _currentScrollDiv.get(0).scrollLeft + Space;

}
/**
 * �·�ֹͣ ����
 * 
 * @param {jquery����}
 *            JQObjScroll JQObjScroll ��Ҫ�����Ĳ�(jquery����)
 */
function scrollRight_stop() {
	clearInterval(MoveTimeObj);
	// ����Ԫ�� ��scrollLeft
	var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
	if (JQObjScroll_scrollLeft % PageWidth - fill != 0) {
		Comp = PageWidth - JQObjScroll_scrollLeft % PageWidth + fill;
		CompScr();
	} else {
		MoveLock = false;
	}
	AutoPlay();
}

// **************************�Ϸ�*********************************
// �Ϸ���ʼ
function ISL_GoUp() {
	if (MoveLock)
		return;
	clearInterval(AutoPlayObj);
	MoveLock = true;
	MoveTimeObj = setInterval(function() {
				// ����Ԫ�� ��scrollLeft
				var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
				// ����Ԫ�� �����list1Ԫ�� ��offsetWidth
				var JQObjScrollList1_offsetWidth = _currentScrollDiv.find(className_listDiv).get(0).offsetWidth;
				if (JQObjScroll_scrollLeft <= 0) {
					_currentScrollDiv.get(0).scrollLeft = JQObjScroll_scrollLeft + JQObjScrollList1_offsetWidth;
				}
				_currentScrollDiv.get(0).scrollLeft = _currentScrollDiv.get(0).scrollLeft - Space;
			}, Speed);
}

// �Ϸ�ֹͣ
function ISL_StopUp() {
	clearInterval(MoveTimeObj);
	// ����Ԫ�� ��scrollLeft
	var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
	if (JQObjScroll_scrollLeft % PageWidth - fill != 0) {
		Comp = fill - (JQObjScroll_scrollLeft % PageWidth);
		CompScr();
	} else {
		MoveLock = false;
	}
	AutoPlay();
}

// ******************ֹͣ���Ʋ���**********************
function CompScr() {
	var num;
	if (Comp == 0) {
		MoveLock = false;
		return;
	}
	// ����Ԫ�� ��scrollLeft
	var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
	if (Comp < 0) { // �Ϸ�
		if (Comp < -Space) {
			Comp += Space;
			num = Space;
		} else {
			num = -Comp;
			Comp = 0;
		}
		_currentScrollDiv.get(0).scrollLeft = JQObjScroll_scrollLeft - num;

	} else { // �·�
		if (Comp > Space) {
			Comp -= Space;
			num = Space;
		} else {
			num = Comp;
			Comp = 0;
		}
		_currentScrollDiv.get(0).scrollLeft = JQObjScroll_scrollLeft + num;
	}
	setTimeout('CompScr()', Speed);
}
