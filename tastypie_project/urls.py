from django.conf.urls import patterns, include, url
from django.contrib import admin
from apiengine.api import MessageModelResource

resource_model = MessageModelResource()

urlpatterns = patterns('',

    url(r'^admin/', include(admin.site.urls)),
    url(r'^api/', include(resource_model.urls)),
)
