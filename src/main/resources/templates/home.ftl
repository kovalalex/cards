<#import "parts/common.ftl" as c>
<#import "parts/menu.ftl" as m>

<@c.page>

<@m.menu></@m.menu>

    <form class="form-upload" method="post" action="/result" enctype="multipart/form-data">
        <h1>Загрузить файл</h1>
        <div class="form-group">

            <div class="custom-file">
                <input type="file" name="file" class="custom-file-input" id="customFile">
                <label class="custom-file-label" for="customFile">Выберите xml файл</label>
            </div>
        </div>

        <button class="btn" type="submit">Обработать</button>
    </form>
</@c.page>