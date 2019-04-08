<#import "parts/common.ftl" as c>
<#import "parts/menu.ftl" as m>
<@c.page>
<@m.menu></@m.menu>
<div class="container">
<div class="row">
    <h3>Информация о сеансах</h3>

</div>


<div class="row">
    <div class="col">
        <p>Онлайн: ${onlineCounter}</p>
        <p>Всего сессий: ${allRequestCounter}</p>
    </div>
</div>

</body>
</@c.page>