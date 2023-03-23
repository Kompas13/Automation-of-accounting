# automation  of accounting

<h3 class="paragraph">Проект - &laquo;Автоматизация бухгалтерии&raquo;.</h3>
<h4>Функционал приложения</h4>
<ol start="1">
<li>Консольный интерфейс для управления программой.</li>
<li>Считывание месячных и годовых отчётов бухгалтерии из файлов.</li>
<li>Сверка данных по месячным и годовым отчётам.</li>
<li>Вывод информации о месячных и годовом отчёте.</li>
</ol>
<h4>Формат входных файлов</h4>
<div class="paragraph">Данные в формате CSV . Приложение работает с двумя видами отчётов:</div>
<ul>
<li>Месячный отчёт, содержащий данные о доходах и расходах в рамках одного календарного месяца. В программе они представляются классом <code class="code-inline code-inline_theme_light">MonthlyReport</code>.</li>
<li>Годовой отчёт, содержащий ровно по 2 записи на каждый из 12 месяцев &mdash; общий доход и расход за этот месяц. Представлен классом <code class="code-inline code-inline_theme_light">YearlyReport</code>.</li>
</ul>
<div class="paragraph">Каждый файл в формате CSV состоит из набора строк. В самой первой строке идут заголовки полей. Далее каждая строка состоит из значений, разделённых символов-разделителем &mdash; запятой.</div>
<div class="paragraph">&nbsp;</div>
<h4>Именование месячных отчётов</h4>
<div class="paragraph">Имена файлов с месячными отчётами имеют формат <code class="code-inline code-inline_theme_light">m.YYYYMM.csv</code>, где:</div>
<ul>
<li><code class="code-inline code-inline_theme_light">m</code> &mdash; буква <code class="code-inline code-inline_theme_light">m</code> в начале файла, чтобы отделить отчёты за месяц и отчёты за год;</li>
<li><code class="code-inline code-inline_theme_light">YYYY</code> &mdash; год. Например, 2021;</li>
<li><code class="code-inline code-inline_theme_light">MM</code> &mdash; месяц строго двумя цифрами. Счёт начинается с единицы, то есть 01 &mdash; &laquo;январь&raquo;, а 11 &mdash; &laquo;ноябрь&raquo;.</li>
</ul>
<div class="paragraph">Пример именований: <code class="code-inline code-inline_theme_light">m.202001.csv</code> &mdash; месячный отчёт за январь 2020 года, <code class="code-inline code-inline_theme_light">m.201912.csv</code> &mdash; месячный отчёт за декабрь 2019 года.</div>
<h4>Именование годовых отчётов</h4>
<div class="paragraph">Имена файлов с годовым отчётом имеют формат <code class="code-inline code-inline_theme_light">y.YYYY.csv</code>, где:</div>
<ul>
<li><code class="code-inline code-inline_theme_light">y</code> &mdash; буква <code class="code-inline code-inline_theme_light">y</code> в начале файла, чтобы отделить отчёты за месяц и отчёты за год;</li>
<li><code class="code-inline code-inline_theme_light">YYYY</code> &mdash; год. Например, 2021.</li>
</ul>
<div class="paragraph">Пример именований: <code class="code-inline code-inline_theme_light">y.2020.csv</code> &mdash; годовой отчёт за 2020 год, <code class="code-inline code-inline_theme_light">y.2018.csv</code> &mdash; годовой отчёт за 2018 год.</div>
<h4>Формат месячного отчёта</h4>
<div class="paragraph">Месячный отчёт содержит информацию о всех тратах, произведённых в течение календарного месяца. Сюда попадает информация как о доходах, так и о расходах парка развлечений.</div>
<div class="paragraph">Пример CSV файла с месячным отчётом:</div>
<div class="code-block code-block_theme_light">
<div class="scrollable-default scrollable scrollable_theme_light code-block__scrollable">
<div>&nbsp;</div>
<div class="scrollable__content-wrapper">
<div class="scrollbar-remover scrollable__content-container">
<div class="scrollable__content">
<pre class="code-block__code-wrapper"><code class="code-block__code">item_name,is_expense,quantity,sum_of_one
Воздушные шарики,TRUE,5000,5
Автоматы с мороженным,TRUE,12,15000
Продажа мороженного,FALSE,1000,120</code></pre>
</div>
</div>
</div>
</div>
</div>
<div class="paragraph">Месячные отчёты состоят из четырёх полей:</div>
<ul>
<li><code class="code-inline code-inline_theme_light">item_name</code> &mdash; название товара;</li>
<li><code class="code-inline code-inline_theme_light">is_expense</code> &mdash; одно из двух значений: <code class="code-inline code-inline_theme_light">TRUE</code> или <code class="code-inline code-inline_theme_light">FALSE</code>. Обозначает, является ли запись тратой (TRUE) или доходом (FALSE);</li>
<li><code class="code-inline code-inline_theme_light">quantity</code> &mdash; количество закупленного или проданного товара;</li>
<li><code class="code-inline code-inline_theme_light">sum_of_one</code> &mdash; стоимость одной единицы товара. Целое число.</li>
</ul>
<h4>Формат годового отчёта</h4>
<div class="paragraph">Годовой отчёт содержит информацию о всех тратах, произведённых в течение года. Он содержит по две записи на каждый месяц. Месяц обозначается строго двумя цифрами, начиная с единицы, то есть 01 &mdash; &laquo;январь&raquo;, а 11 &mdash; &laquo;ноябрь&raquo;.</div>
<div class="paragraph">Пример CSV файла с годовым отчётом:</div>
<div class="code-block code-block_theme_light">
<div class="scrollable-default scrollable scrollable_theme_light code-block__scrollable">
<div class="scrollable__content-wrapper">
<div class="scrollbar-remover scrollable__content-container">
<div class="scrollable__content">
<pre class="code-block__code-wrapper"><code class="code-block__code">month,amount,is_expense
01,100000,false
01,30000,true
02,321690,false
02,130000,true
03,999999,true
03,999999,false</code></pre>
</div>
</div>
</div>
<section class="scrollbar-default scrollbar scrollbar_vertical scrollbar_hidden scrollable__scrollbar scrollable__scrollbar_type_vertical">
<div class="scrollbar__control-container">
<div class="scrollbar__control">
<div class="scrollbar__control-line">&nbsp;</div>
</div>
</div>
</section>
</div>
</div>
<div class="paragraph">Строка годового отчёта состоит из трёх полей:</div>
<ul>
<li><code class="code-inline code-inline_theme_light">month</code> &mdash; месяц. Целое число;</li>
<li><code class="code-inline code-inline_theme_light">amount</code> &mdash; сумма;</li>
<li><code class="code-inline code-inline_theme_light">is_expense</code> &mdash; одно из двух значений: <code class="code-inline code-inline_theme_light">true</code> или <code class="code-inline code-inline_theme_light">false</code>. Обозначает, является ли запись тратой (true) или доходом (false).</li>
</ul>
<h4>Консольный интерфейс</h4>
<div class="paragraph">Консольный интерфейс по работе с программной позволяет оператору произвести одно из пяти действий по выбору:</div>
<ol start="1">
<li>Считать все месячные отчёты</li>
<li>Считать годовой отчёт</li>
<li>Сверить отчёты</li>
<li>Вывести информацию о всех месячных отчётах</li>
<li>Вывести информацию о годовом отчёте</li>
</ol>
<p>&nbsp;</p>
