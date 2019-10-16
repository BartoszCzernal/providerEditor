
$(function () {
  $('select').change(function() {
    var used = new Set;
    $('select').each(function () {
      var reset = false;
      $('option', this).each(function () {
        var hide = used.has($(this).text());
        if (hide && $(this).is(':selected') && this.value.length > 0) reset = true;
        $(this).prop('disabled', hide);
      });
      if (reset) $('option:not([disabled]):first', this).prop('selected', true);  
      used.add($('option:selected', this).text());
    });
  }).trigger('change');
});
