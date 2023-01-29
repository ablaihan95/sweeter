<#import "parts/common.ftlh" as c>
<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" method="get" action="/main">
            <label>
                <input type="text" name="filter" class="form-control" placeholder="search by tag">
            </label>
            <button class="btn btn-primary ml-2" type="submit">Search</button>
        </form>
    </div>
</div>
<div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new message...
    </a>
    <div id="collapseExample" class="collapse <#if message??>show</#if>">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input class="form-inline ${(textError??)?string('is-invalid', "")}"
                           value="<#if message??>${message.text}</#if>" type="text" name="text"
                           placeholder="enter value"/>
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <label>
                        <input class="form-inline" type="text" name="tag" placeholder="enter tag">
                    </label>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label for="customFile" class="custom-file-label">Choice file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Add</button>
                </div>
            </form>
            <div>
            </div>
        </div>
        <div>
            <p>${error!}</p>
        </div>
    </div>
    <div class="card-columns">
        <#list messages as message>
            <div class="card my-3" style="width: 18rem">
                <div>
                    <#if message.filename??>
                        <img src="/img/${message.filename}" class="card-img-top">
                    </#if>
                </div>
                <div class="m-2">
                    <div>
                        <span>${message.text}</span>
                        <i>${message.tag}</i>
                    </div>
                    <div class="card-footer text-muted">
                        <strong>${message.authorName!}</strong>
                    </div>
                </div>
            </div>
        </#list>
    </div>
    </@c.page>
