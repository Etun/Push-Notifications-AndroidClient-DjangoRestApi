# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('apiengine', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='messagemodel',
            name='device_id',
            field=models.CharField(default=b'Any', max_length=200),
            preserve_default=True,
        ),
    ]
