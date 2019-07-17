var arrayIdsElementsPage = new Array;

/**
 * Carrega um array global com os ids de todos os componentes da página Para ter
 * faciliades em obtenção de valores dos componentes bem como trabalhar com ajax
 */
function loaderIdsElementsPage() {
	arrayIdsElementsPage = new Array;
	for (form = 0; form <= document.forms.length; form++) {
		var formCurrent = document.forms[form];
		if (formCurrent != undefined) {
			for (i = 0; i < document.forms[form].elements.length; i++) {
				if (document.forms[form].elements[i].id != '') {
					arrayIdsElementsPage[i] = document.forms[form].elements[i].id;
				}
			}
		}
	}
}

/**
 * Retorna o valor do id do componente dentro do documento html passando como
 * parâmetro a descrição do id declarada em jsf
 * 
 * @param id
 */
function getValueElementById(id) {
	loaderIdsElementsPage();
	for (i = 0; i < arrayIdsElementsPage.length; i++) {
		var value = "" + arrayIdsElementsPage[i];
		if (value.indexOf(id) > -1) {
			return value;
		}
	}
	return idundefined;
}

function logout(contextPath) {
	var post = 'invalidate_session';

	$.ajax({
		type : "POST",
		url : post
	}).always(function(response) {
		document.location = contextPath + '/j_spring_security_logout';
	});
}

function invalidateSession(context, page) {
	document.location = (context + page + ".jsf");
}

function validateUserPassword() {
	j_username = document.getElementById("j_username").value;
	j_password = document.getElementById("j_password").value;

	if (j_username === null || j_username.trim() === '') {
		alert("Informe o usuário!");
		$('#j_username').focus();
		return false;
	}

	if (j_password === null || j_password.trim() === '') {
		alert("Informe a Senha!");
		$('#j_password').focus();
		return false;
	}
	return true;
}

function openMenuUser() {
	jQuery("#divMenuUser").show('slow').mouseleave(function() {
		closeMenuUser();
	});
}

function closeMenuUser() {
	if (jQuery("#divMenuUser").is(":visible")) {
		jQuery("#divMenuUser").hide("slow");
	}
}

function redirectPage(context, page) {
	page = page + ".jsf";
	document.location = context + page;
}

function localeDatePtBr() {
	PrimeFaces.locales['pt'] = {
		closeText : 'Fechar',
		prevText : 'Anterior',
		nextText : 'Próximo',
		currentText : 'Começo',
		monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio',
				'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro',
				'Dezembro' ],
		monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul',
				'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
		dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta',
				'Sexta', 'Sábado' ],
		dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
		dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
		weekHeader : 'Semana',
		firstDay : 0,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : '',
		timeOnlyTitle : 'São Horas',
		timeText : 'Tempo',
		hourText : 'Hora',
		minuteText : 'Minuto',
		secondText : 'Segundo',
		ampm : false,
		month : 'Mês',
		week : 'Semana',
		day : 'Dia',
		allDayText : 'Todo o Dia'
	};
}

function initTamplete() {
	jQuery(document).ready(function($) {
		$('#divMenuUser').hide();
		$('#divMenuLeft').hide();
		$('#divMenuLeft').css("left", "-200px");
		$('#spanIconMenuLeft').click(function() {
			if ($('#divMenuLeft').is(':visible')) {
				hideMenuLeft();
			} else {
				$('#divMenuLeft').show();
				$('#divMenuLeft').animate({
					"left" : "0px"
				}, "slow");
			}
		});
	});
}

function hideMenuLeft() {
	jQuery('#divMenuLeft').animate({
		"left" : "-200px"
	}, "slow", function() {
		$('#divMenuLeft').hide();
	});
}

function addFocusInField(field) {
	var id = getValueElementById(field);
	if (id != undefined) {
		document.getElementById(id).focus();
	}
}

function managementEnterKey() {
	$(document).ready(function() {
		$('input').keypress(function(e) {
			var code = null;
			code = (e.keyCode ? e.keyCode : e.which);
			return (code === 13) ? false : true;
		});

		$('input[type=text]').keydown(function(e) {
			// Obter o pr󸩭o ?ice do elemento de entrada de texto
			var next_idx = $('input[type=text]').index(this) + 1;

			// Obter o número de elemento de entrada de texto em um documento
			// html
			var tot_idx = $('body').find('input[type=text]').length;

			// Entra na tecla no c󤩧o ASCII
			if (e.keyCode === 13) {
				if (tot_idx === next_idx)
					// Vᠰara o primeiro elemento de texto
					$('input[type=text]:eq(0)').focus();
				else
					// Vᠰara o elemento de entrada de texto seguinte
					$('input[type=text]:eq(' + next_idx + ')').focus();
			}
		});
	});
}

function toggleTableColors() {
	/* recebe uma lista com todos os elementos de dtbObjects com a tag <tr> */
	var table = document.getElementById("dtbObjects").getElementsByTagName('tr');
	for (i = 0; i < table.length; i++) {
		if (i % 2 === 0) {
			table[i].onmouseover = function() {
				this.style.background = '#EE2B2B'
			}
			table[i].onmouseout = function() {
				this.style.background = '#EEE'
			}
		} else {
			table[i].onmouseover = function() {
				this.style.background = '#EE2B2B'
			}
			table[i].onmouseout = function() {
				this.style.background = '#B1ACA6'
			}
		}
	}
}