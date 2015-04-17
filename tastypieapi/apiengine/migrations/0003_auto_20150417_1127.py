# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('apiengine', '0002_messagemodel_device_id'),
    ]

    operations = [
        migrations.AlterField(
            model_name='messagemodel',
            name='device_id',
            field=models.CharField(max_length=200),
        ),
    ]
