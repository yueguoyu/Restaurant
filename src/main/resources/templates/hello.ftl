<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/addMenu" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="exampleInputEmail1">name：</label><input type="email" class="form-control" id="exampleInputEmail1" />
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">weather</label><input type="password" class="form-control" id="exampleInputPassword1" />
                            </div>
                            <div class="form-group">
                                <label for="exampleInputFile">File input</label><input type="file" id="exampleInputFile" />
                                <p class="help-block">
                                    Example block-level help text here.
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">price：</label><input type="price" class="form-control" id="exampleInputEmail1" />
                            </div>
                            <div class="season">
                                <label for="exampleInputEmail1">season：</label>
                                <label class="radio">
                                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>  1
                                </label>
                                <label class="radio">
                                    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2"> 2
                                </label>
                                <label class="radio">
                                    <input type="radio" name="optionsRadios" id="optionsRadios3" value="option2">3
                                </label>
                                <label class="radio">
                                    <input type="radio" name="optionsRadios" id="optionsRadios4" value="option2">4
                                </label>
                                <label class="radio">
                                    <input type="radio" name="optionsRadios" id="optionsRadios5" value="option2">5
                                </label>
                            </div>
                            <div class="hotcold">
                                <label for="exampleInputEmail1">hotcold：</label>
                                <label class="radio">
                                    <input type="radio" name="hotcold" id="optionsRadios1" value="option1" checked>  1
                                </label>
                                <label class="radio">
                                    <input type="radio" name="hotcold" id="optionsRadios2" value="option2"> 2
                                </label>
                                <label class="radio">
                                    <input type="radio" name="hotcold" id="optionsRadios3" value="option2">3
                                </label>
                                <label class="radio">
                                    <input type="radio" name="hotcold" id="optionsRadios4" value="option2">4
                                </label>
                                <label class="radio">
                                    <input type="radio" name="hotcold" id="optionsRadios5" value="option2">5
                                </label>
                            </div>
                            <div class="menu_hot">
                                <label for="exampleInputEmail1">hot：</label>
                                <label class="radio">
                                    <input type="radio" name="menu_hot" id="optionsRadios1" value="option1" checked>  1
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_hot" id="optionsRadios2" value="option2"> 2
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_hot" id="optionsRadios2" value="option2">3
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_hot" id="optionsRadios2" value="option2">4
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_hot" id="optionsRadios2" value="option2">5
                                </label>
                            </div>

                            <div class="menu_acid">
                                <label for="exampleInputEmail1">acid：</label>
                                <label class="radio">
                                    <input type="radio" name="menu_acid" id="optionsRadios1" value="option1" checked>  1
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_acid" id="optionsRadios2" value="option2"> 2
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_acid" id="optionsRadios2" value="option2">3
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_acid" id="optionsRadios2" value="option2">4
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_acid" id="optionsRadios2" value="option2">5
                                </label>
                            </div>
                            <!-甜度-->
                            <div class="menu_sweet">
                                <label for="exampleInputEmail1">sweet：</label>
                                <label class="radio">
                                    <input type="radio" name="menu_sweet" id="optionsRadios1" value="option1" checked>  1
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_sweet" id="optionsRadios2" value="option2"> 2
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_sweet" id="optionsRadios3" value="option2">3
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_sweet" id="optionsRadios4" value="option2">4
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_sweet" id="optionsRadios5" value="option2">5
                                </label>
                            </div>
                            <!--bitter-->
                            <div class="menu_bitter">
                                <label for="exampleInputEmail1">bitter：</label>
                                <label class="radio">
                                    <input type="radio" name="menu_bitter" id="optionsRadios1" value="option1" checked>  1
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_bitter" id="optionsRadios2" value="option2"> 2
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_bitter" id="optionsRadios2" value="option2">3
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_bitter" id="optionsRadios2" value="option2">4
                                </label>
                                <label class="radio">
                                    <input type="radio" name="menu_bitter" id="optionsRadios2" value="option2">5
                                </label>
                            </div>

                            <div class="checkbox">
                                <label><input type="checkbox" />Check me out</label>
                            </div> <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>