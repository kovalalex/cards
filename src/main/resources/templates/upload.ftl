<#import "parts/common.ftl" as c>
<#import "parts/menu.ftl" as m>
<@c.page>
<@m.menu></@m.menu>
<div class="container">
<div class="row">
    <h3>Загружено, распознано карточек: ${employees_count}</h3>

</div>


<div class="row">
<table class="table">
    <thead>
    <tr>

        <th scope="col">Имя</th>
        <th scope="col">Фамилия</th>
        <th scope="col">Отчество</th>
        <th scope="col">Должность</th>
        <th scope="col">Список телефонов</th>
        <th scope="col">Список Email</th>

    </tr>
    </thead>
    <tbody>
    <#list employees as e>
    <tr>
        <td>${e.firstname}</td>
        <td>${e.lastname}</td>
        <td>${e.secondname}</td>
        <td>${e.position}</td>
        <td>
            <#list e.phoneList as phone>${phone}<#sep>, </#list>
        </td>
        <td>
            <#list e.emailList as mail>${mail}<#sep>, </#list>
        </td>
    </tr>
    </#list>

    </tbody>
</table>
</div>

</body>
</@c.page>