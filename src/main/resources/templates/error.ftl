<#import "parts/centerHeight.ftl" as c>

<@c.page>
<div class="page-wrap d-flex flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 text-center">
                <span class="display-1 d-block">Ошибка</span>
                <div class="mb-4 lead">${error}</div>
                <a href="/" class="btn btn-link">На главную</a>
            </div>
        </div>
    </div>
</div>

</@c.page>