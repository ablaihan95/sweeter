<#import "parts/common.ftlh" as c>
<@c.page>
    <h5>${username}</h5>
    ${message?ifExists}
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <label>
                    <input type="password" name="password" class="form-control"/>
                </label>
            </div>
        </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Email: </label>
                <div class="col-sm-6">
                    <label>
                        <input type="email" name="email" class="form-control", value="${email!''}"/>
                    </label>
                </div>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div >
            <button class="btn btn-primary" type="submit">save</button>
        </div>
    </form>
    <a href="/registration">sign up</a></#if>
</@c.page>