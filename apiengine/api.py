from tastypie.resources import ModelResource
from apiengine.models import MessageModel
from tastypie.serializers import Serializer


class MessageModelResource(ModelResource):
    class Meta:
        queryset = MessageModel.objects.all()
        resource_name = 'message'
        serializer = Serializer(formats=['json'])
