LinkController = function() {
    this.element = $('#links');
}

LinkController.prototype.getLinks = function() {
    $.ajax({
        type:"GET",
        url:"/getMainMap",
        async:false,
        context: this,
        dataType: "json",
        success: function(data) {
            this.indexMap = data;
        }
    });
}

LinkController.prototype.createMenu = function() {
    this.getLinks();
//    $('p:gt(1)', this.element).detach();
    for(var key in this.indexMap) {
        $('<p/>')
            .append($('<a href="'+ key +'"/>').html(this.indexMap[key]))
            .appendTo(this.element);
    }
}

LinkController.prototype.show = function() {
//    this.createMenu();
    this.element.show('slow', 'swing', '');
}

LinkController.prototype.hide = function() {
    this.element.hide('slow', 'swing', '');
}

//$(document).ready
var tratata = (function() {
    var linkController = new LinkController();
    var onShow = (function() {
        return function() {
            linkController.show();
        }
    })();
    var onHide = (function() {
        return function() {
            linkController.hide();
        }
    })();

    $('#buttons')
        .append($('<button/>').html('Show').on('click', onShow))
        .append($('<button/>').html('Hide').on('click', onHide))
    linkController.createMenu();
})