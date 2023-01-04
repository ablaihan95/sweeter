<#macro login path isRegistration>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <label>
                    <input type="password" name="password" class="form-control"/>
                </label>
            </div>
        </div>
        <#if isRegistration>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Firstname: </label>
                <div class="col-sm-6">
                    <label>
                        <input type="text" name="firstname" class="form-control"/>
                    </label>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Email: </label>
                <div class="col-sm-6">
                    <label>
                        <input type="email" name="email" class="form-control"/>
                    </label>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div>
            <button class="btn btn-primary" type="submit"><#if isRegistration>Sign up<#else>Sign In</#if></button>
        </div>
    </form>
    <#if !isRegistration><a href="/registration">sign up</a></#if>
</#macro>
<#--<#macro reg>-->
<#--    <form action="${path}" method="post">-->
<#--        <div class="form-group row">-->
<#--            <label class="col-sm-2 col-form-label"> User Name : </label>-->
<#--            <div class="col-sm-6x   ">-->
<#--                <input type="text" name="username" class="form-control"/>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="form-group row">-->
<#--            <label class="col-sm-2 col-form-label"> Password: </label>-->
<#--            <div class="col-sm-6">-->
<#--                <label>-->
<#--                    <input type="password" name="password" class="form-control"/>-->
<#--                </label>-->
<#--            </div>-->
<#--        </div>-->
<#--       -->
<#--        <div> <button class="btn btn-primary" type="submit">Sign Up</button></div>-->
<#--    </form>-->
<#--</#macro>-->
<#macro logout>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit">Sign Out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>